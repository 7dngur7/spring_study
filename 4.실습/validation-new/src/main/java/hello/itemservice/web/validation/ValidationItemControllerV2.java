package hello.itemservice.web.validation;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/validation/v2/items")
@RequiredArgsConstructor
public class ValidationItemControllerV2 {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "validation/v2/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "validation/v2/item";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("item", new Item());
        return "validation/v2/addForm";
    }

    //@PostMapping("/add")
    public String addItemV1(@ModelAttribute Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {

        if(!StringUtils.hasText(item.getItemName())){
            bindingResult.addError(new FieldError( "item","itemName", "이름을 입력해주세요"));
        }

        if(item.getPrice()==null || item.getPrice()>1000000 || item.getPrice()<1000 ){
            bindingResult.addError(new FieldError( "item","price", "가격을 올바르게 해 주세요"));

        }

        if(item.getQuantity()==null || item.getQuantity()>10000){
            bindingResult.addError(new FieldError( "item","quantity", "수량을 올바르게 해 주세요"));

        }

        if(item.getPrice() !=null && item.getQuantity() !=null && item.getPrice()* item.getQuantity()<10000){
            bindingResult.addError(new ObjectError("globalError", "수량과 가격의 곱이 10000 이상이어야 합니다. 현재: "+item.getPrice()* item.getQuantity()));
        }

        if(bindingResult.hasErrors()){
            log.info("item: {}",item);
            //log.info("error:{}", bindingResult);
            return "validation/v2/addForm";
        }


        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v2/items/{itemId}";
    }

    @PostMapping("/add")
    public String addItemV2(@ModelAttribute Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {

        if(!StringUtils.hasText(item.getItemName())){
            bindingResult.addError(new FieldError( "item","itemName", item.getItemName(), false, null,null,"이름을 입력해주세요"));
        }

        if(item.getPrice()==null || item.getPrice()>1000000 || item.getPrice()<1000 ){
            bindingResult.addError(new FieldError( "item","price", item.getPrice(), false, null,null, "가격을 올바르게 해 주세요"));

        }

        if(item.getQuantity()==null || item.getQuantity()>10000){
            bindingResult.addError(new FieldError( "item","quantity", item.getQuantity(), false, null,null, "수량을 올바르게 해 주세요"));

        }

        if(item.getPrice() !=null && item.getQuantity() !=null && item.getPrice()* item.getQuantity()<10000){
            bindingResult.addError(new ObjectError("globalError",null,null, "수량과 가격의 곱이 10000 이상이어야 합니다. 현재: "+item.getPrice()* item.getQuantity()));
        }

        if(bindingResult.hasErrors()){
            log.info("item: {}",item);
            //log.info("error:{}", bindingResult);
            return "validation/v2/addForm";
        }


        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v2/items/{itemId}";
    }


    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "validation/v2/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/validation/v2/items/{itemId}";
    }

}

