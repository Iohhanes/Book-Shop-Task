package com.academia.bookshop.service.impl;

import com.academia.bookshop.mappers.BookMapper;
import com.academia.bookshop.model.dto.request.AddBookRequestDto;
import com.academia.bookshop.model.dto.response.BookDto;
import com.academia.bookshop.model.entity.Book;
import com.academia.bookshop.repository.BookRepository;
import com.academia.bookshop.service.BookService;
import com.academia.bookshop.service.KafkaProducerService;
import com.academia.bookshop.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final StorageService storageService;
    private final KafkaProducerService kafkaProducerService;

    @Transactional(readOnly = true)
    @Override
    public List<BookDto> findAll() {
        List<Book> books = bookRepository.findAll();
        return bookMapper.fromEntities(books);
    }

    @Transactional(readOnly = true)
    @Override
    public List<BookDto> search(String searchValue) {
        if (StringUtils.hasLength(searchValue)) {
            List<Book> books = bookRepository.search(searchValue.toLowerCase());
            return bookMapper.fromEntities(books);
        } else {
            return Collections.emptyList();
        }
    }

    @Transactional
    @Override
    public BookDto add(AddBookRequestDto addBookRequestDto, MultipartFile image) throws IOException {
        Book book = bookMapper.fromAddRequestDto(addBookRequestDto);

        if (image != null && !image.isEmpty()) {
            book.setImageUrl(storageService.upload(image));
        }

        book = bookRepository.save(book);
        kafkaProducerService.sendNewBookCreationEvent(book);
        return bookMapper.fromEntity(book);
    }

    @Transactional
    @Override
    public void remove(List<Integer> ids) {
        List<Book> books = bookRepository.findAllById(ids);
        String[] fileUrls = books.stream().map(Book::getImageUrl).toArray(String[]::new);

        bookRepository.deleteAllByIdInBatch(ids);

        storageService.delete(fileUrls);
    }
}
