package com.yazao.spring.service;

import com.yazao.spring.model.Book;
import com.yazao.spring.model.Category;

import java.util.List;

/**
 */
public interface BookService {

    /**
     * 获取所有的分类 集合
     *
     * @return
     */
    List<Category> getAllCategories();

    /**
     * 根据id 获取分类
     *
     * @param id
     * @return
     */
    Category getCategory(int id);

    /**
     * 获取所有书 集合
     *
     * @return
     */
    List<Book> getAllBooks();

    /**
     * 根据id 获取书
     *
     * @param id
     * @return
     */
    Book get(long id);
}
