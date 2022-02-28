package com.test.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class TestListDTO extends CommonDTO {

    // 문제집 ID
    private String testListId;
    // 과목 코드
    private int subjectCode;

}
