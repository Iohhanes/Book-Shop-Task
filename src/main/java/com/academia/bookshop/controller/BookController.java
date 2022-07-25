package com.academia.bookshop.controller;

import com.academia.bookshop.model.dto.request.AddBookRequestDto;
import com.academia.bookshop.model.dto.response.BookDto;
import com.academia.bookshop.serive.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.academia.bookshop.constants.UrlConstants.API;

@RestController
@RequestMapping(API + "/books")
@RequiredArgsConstructor
@Tag(name = "Book")
public class BookController {
    private final BookService bookService;

    @Operation(summary = "Get books", description = "Get list of all books")
    @ApiResponse(
            responseCode = "200",
            description = "Ok",
            content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(
                            schema = @Schema(
                                    implementation = BookDto.class
                            )
                    )
            )
    )
    @GetMapping
    public List<BookDto> getAll() {
        return bookService.findAll();
    }

    @Operation(summary = "Search books", description = "Search books by title ignoring case or tag's name")
    @ApiResponse(
            responseCode = "200",
            description = "Ok",
            content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(
                            schema = @Schema(
                                    implementation = BookDto.class
                            )
                    )
            )
    )
    @GetMapping("/search")
    public List<BookDto> search(@RequestParam(value = "search", required = false) String search) {
        return bookService.search(search);
    }

    @PostMapping
    public BookDto add(@RequestBody AddBookRequestDto addBookRequestDto) {
        return bookService.add(addBookRequestDto);
        //@RequestPart("image") MultipartFile file
        //return storageService.uploadFile(file);
    }
}
