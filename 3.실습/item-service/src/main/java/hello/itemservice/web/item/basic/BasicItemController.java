package hello.itemservice.web.item.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/basic/items")
public class BasicItemController {
    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model){
        List<Item> items = new ArrayList<>(itemRepository.findAll());
        model.addAttribute("items", items);
        log.info("basic items");
        return ("/basic/items");
    }

    @GetMapping("/{itemId}")
    public String specific(@PathVariable("itemId")Long itemId,Model model){
        model.addAttribute("item",itemRepository.findById(itemId));

        return ("/basic/item");
    }

    @GetMapping("/add")
    public String addProduct(){
        return ("basic/addForm");
    }
//    @PostMapping("/add")
//    public String addItemV1(@RequestParam("itemName") String itemName, @RequestParam("price")Integer price, @RequestParam("quantity") Integer quantity, Model model){
//        Item item = new Item(itemName,price,quantity);
//        itemRepository.save(item);
//        model.addAttribute("item", item);
//
//        return ("basic/item");
//    }

    @PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item,RedirectAttributes redirectAttributes){
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("saveState", true);

        return ("redirect:/basic/items/{itemId}");
    }

    @GetMapping("/{itemId}/edit")
    public String editItemForm(@PathVariable("itemId")Long id, Model model){
        Item item = itemRepository.findById(id);
        model.addAttribute("item", item);
        return ("basic/editForm");
    }

    @PostMapping("/{itemId}/edit")
    public String editItemV1(@PathVariable("itemId") Long id, @ModelAttribute("item") Item item){
        itemRepository.update(id, item);
        return ("redirect:/basic/items/{itemId}");
    }

    @PostConstruct
    public void test(){
        itemRepository.save(new Item("itemA", 10000, 500));
        itemRepository.save(new Item("itemB", 20000, 50));
    }


}
