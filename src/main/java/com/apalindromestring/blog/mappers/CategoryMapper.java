package com.apalindromestring.blog.mappers;

import com.apalindromestring.blog.domain.PostStatus;
import com.apalindromestring.blog.domain.dtos.CategoryDto;
import com.apalindromestring.blog.domain.dtos.CreateCategoryRequest;
import com.apalindromestring.blog.domain.entities.Category;
import com.apalindromestring.blog.domain.entities.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

    @Mapping(target = "postCount", source = "posts", qualifiedByName = "calculatePostCount")
    CategoryDto ToDto(Category category);


    Category toEntity(CreateCategoryRequest createCategoryRequest);

    @Named("calculatePostCount")
    default long calculatePostCount(List<Post> posts) {
        if (posts == null) {
            return 0;
        }

        return posts.stream()
                .filter(post -> PostStatus.PUBLISHED.equals(post.getStatus()))
                .count();
    }


}
