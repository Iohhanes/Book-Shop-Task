package com.academia.bookshop.controller;

import com.academia.bookshop.model.dto.request.AddAuthorRequestDto;
import com.academia.bookshop.model.dto.response.AuthorDto;
import com.academia.bookshop.serive.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.academia.bookshop.constants.UrlConstants.API;

@RestController
@RequestMapping(API + "/authors")
@RequiredArgsConstructor
@Tag(name = "Author")
public class AuthorController {
    private final AuthorService authorService;

    @Operation(summary = "Get authors", description = "Get list of all authors")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AuthorDto> getAll() {
        return authorService.findAll();
    }

    @Operation(summary = "Search authors", description = "Search authors by first name and last name ignoring case")
    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AuthorDto> search(@RequestParam(value = "value", required = false) String searchValue) {
        return authorService.search(searchValue);
    }

    @Operation(summary = "Add a author", description = "Add a new author")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(
            value = "/add",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public AuthorDto add(@RequestBody @Parameter(description = "Necessary information to create a new author") AddAuthorRequestDto addAuthorRequestDto)  {
        return authorService.add(addAuthorRequestDto);
    }

    @Operation(summary = "Remove authors", description = "Remove authors by ids")
    @ApiResponse(
            responseCode = "204",
            description = "No Content"
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping(value = "/remove", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void remove(@RequestBody @Parameter(description = "List of author ids") List<Long> ids) {
        authorService.remove(ids);
    }
}
