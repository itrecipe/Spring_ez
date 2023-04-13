package com.kook.ex00.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Setter;
import lombok.Data;

@Component
@Data
public class Restaurant {
//Data어노테이션은 equals, canEqual, hashCode toString Chef(), getter 자동 생성

@Setter(onMethod_=@Autowired)
private Chef chef;

}
