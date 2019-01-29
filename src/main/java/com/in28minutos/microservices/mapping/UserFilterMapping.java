package com.in28minutos.microservices.mapping;

import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("UserPostsFilter")
public abstract class UserFilterMapping {

}
