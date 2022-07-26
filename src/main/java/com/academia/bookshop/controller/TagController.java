package com.academia.bookshop.controller;

import com.academia.bookshop.model.dto.request.AddTagRequestDto;
import com.academia.bookshop.model.dto.response.TagDto;
import com.academia.bookshop.serive.TagService;
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
@RequestMapping(API + "/tags")
@RequiredArgsConstructor
@Tag(name = "Tag")
public class TagController {
    private final TagService tagService;

    @Operation(summary = "Get tags", description = "Get list of all tags")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TagDto> getAll() {
        return tagService.findAll();
    }

    @Operation(summary = "Search tags", description = "Search tags by title ignoring case")
    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TagDto> search(@RequestParam(value = "value", required = false) String searchValue) {
        return tagService.search(searchValue);
    }

    @Operation(summary = "Add a tag", description = "Add a new tag")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(
            value = "/add",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public TagDto add(@RequestBody @Parameter(description = "Necessary information to create a new tag") AddTagRequestDto addTagRequestDto)  {
        return tagService.add(addTagRequestDto);
    }

    @Operation(summary = "Remove tags", description = "Remove tags by ids")
    @ApiResponse(
            responseCode = "204",
            description = "No Content"
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping(value = "/remove", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void remove(@RequestBody @Parameter(description = "List of tag ids") List<Long> ids) {
        tagService.remove(ids);
    }
}
