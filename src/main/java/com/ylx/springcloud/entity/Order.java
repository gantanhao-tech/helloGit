package com.ylx.springcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author yanglx
 * @version 1.0
 * @date 2020-05-05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {

    private Long id;
    private String orderNo;
}
