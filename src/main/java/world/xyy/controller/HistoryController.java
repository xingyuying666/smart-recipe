package world.xyy.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import world.xyy.entity.History;


/**
 * Historical controller
 *
 * @author XUEW
 */
@RestController
@RequestMapping("history")
public class HistoryController extends BaseController<History> {

}
