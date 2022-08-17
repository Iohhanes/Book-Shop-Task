package com.academia.bookshop.controller;

import com.academia.bookshop.model.dto.request.AddBookRequestDto;
import com.academia.bookshop.model.dto.response.BookDto;
import com.academia.bookshop.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

import static com.academia.bookshop.constants.UrlConstants.API;
import static com.academia.bookshop.security.AuthoritiesConstants.*;

@RestController
@RequestMapping(API + "/books")
@RequiredArgsConstructor
@Tag(name = "Book")
public class BookController {
    private final BookService bookService;

    @Operation(summary = "Get books", description = "Get list of all books")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BookDto> getAll() {
        return bookService.findAll();
    }

    @Operation(summary = "Search books", description = "Search books by title ignoring case or tag's title")
    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BookDto> search(@RequestParam(value = "value", required = false) String searchValue) {
        return bookService.search(searchValue);
    }

    @Operation(summary = "Add a book", description = "Add a new book with image")
    @PreAuthorize("hasAuthority('" + AUTHORITY_ALL + "') or hasAuthority('" + AUTHORITY_CREATE + "')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(
            value = "/add",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public BookDto add(
            @RequestPart(value = "image", required = false) @Parameter(description = "Image of a book") MultipartFile image,
            @RequestPart("body") @Valid
            @Parameter(description = "Necessary information to create a new book", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
                    AddBookRequestDto addBookRequestDto) throws IOException {

        return bookService.add(addBookRequestDto, image);
    }

    @Operation(summary = "Remove books", description = "Remove books by ids")
    @ApiResponse(
            responseCode = "204",
            description = "No Content"
    )
    @PreAuthorize("hasAuthority('" + AUTHORITY_ALL + "') or hasAuthority('" + AUTHORITY_DELETE + "')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping(value = "/remove", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void remove(@RequestBody @Parameter(description = "List of book ids") List<Integer> ids) {
        bookService.remove(ids);
    }
}
