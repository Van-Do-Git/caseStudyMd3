package com.case3.service.category;

import com.case3.model.Category;
import com.case3.service.IService;

import java.util.List;
import java.util.Locale;

public interface ICategoryService extends IService<Category> {
    List<Category> findAllByUserId(int id_user);
}
