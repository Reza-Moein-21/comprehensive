package ir.comprehensive.controller.humanresource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class HumanResourceController {
    HumanResourcePersonController personController;
    HumanResourceCategoryController categoryController;

    @Autowired
    public HumanResourceController(HumanResourceCategoryController categoryController, HumanResourcePersonController personController) {
        this.categoryController = categoryController;
        this.personController = personController;
    }
}
