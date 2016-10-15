package sample.springboot.web;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sample.springboot.Hoge;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping(method=RequestMethod.POST)
    public Hoge hello(@RequestBody Hoge param) {
        System.out.println(param);

        Hoge hoge = new Hoge();
        hoge.id = 20;
        hoge.name = "Response";

        return hoge;
    }

    @RequestMapping(method=RequestMethod.GET)
    public String getMethod() {
        return "get";
    }

    @RequestMapping(value="/post", method=RequestMethod.POST)
    public String postMethod1() {
        return "post1";
    }
}
