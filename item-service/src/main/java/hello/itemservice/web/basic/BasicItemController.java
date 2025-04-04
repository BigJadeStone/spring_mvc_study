package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor // 아래 생성자 생략가능
public class BasicItemController {

    private final ItemRepository itemRepository;

    /*spirng에서 생성자가 아래와 같이 하나만 있으면
    * @Autowired 생략가능*/
//    @Autowired
//    public BasicItemController(ItemRepository itemRepository) { //@RequiredArgsConstructor 있으면 해당 생성자 생략 가능
//        this.itemRepository = itemRepository;
//    }

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

//    @PostMapping("/add")
    public String addItemV1(@RequestParam String itemName,
                       @RequestParam int price,
                       @RequestParam Integer quantity,
                       Model model) {
        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemRepository.save(item);

        model.addAttribute("item", item);

        return "basic/item";
    }

//    @PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item, Model model) {
        itemRepository.save(item);

//        model.addAttribute("item", item); //자동 추가, 생략 가능

        return "basic/item";
    }

//    @PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item) {
        //@ModelAttribute("item") 이름을 생략하면 클래스명에서 첫번째 글자를 소문자로 한 것을 key로 model에 담아줌.

        itemRepository.save(item);
//        model.addAttribute("item", item); //자동 추가, 생략 가능

        return "basic/item";
    }

    /*
    * @ModelAttribute 생략 가능, 단순타입의 경우 @RequeqstParam이 사용됨. 객체는 @ModelAttribute 자동 사용
    * 이때 model 에 담기는 value의 키는 클래스명에서 첫 글자가 소문자인 거.
    * */
//    @PostMapping("/add")
    public String addItemV4(Item item) {
        itemRepository.save(item);
        return "basic/item";
    }

//    @PostMapping("/add")
    public String addItemV5(Item item) {
        itemRepository.save(item);
        return "redirect:/basic/items/" + item.getId();
    }

    @PostMapping("/add")
    public String addItemV6(Item item, RedirectAttributes redirectAttributes) {
        Item saveItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", saveItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/basic/items/{itemId}";    //status 처럼 경로변수에 담지 않은 것은 ?status=true 이렇게 쿼리파라미터로 변환해줌.
//        return "redirect:/basic/items/{itemId}/{status}"; //{status}도 이렇게 넣어주면 패스배리어블에 나오는 거 확인 완료
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId}"; //@PathVariable Long itemId 값이 {itemId}에 들어온다고 함.
    }

    /*
    * 테스트용 데이터 추가
    * */
    @PostConstruct
    public void init() {
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }

}
