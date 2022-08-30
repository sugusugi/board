package com.example.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import com.example.board.repository.PostRepository;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.board.repository.PostFactory;

import com.example.board.repository.Post;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Optional;

import org.springframework.validation.annotation.Validated;

import com.example.board.validation.GroupOrder;

//エラーメッセージの取得で利用
//import org.springframework.validation.ObjectError;



/**
 * 掲示板のフロントコントローラー.
 */
@Controller
public class BoardController {
    
    /**　投稿の一覧　*/
    @Autowired
    private PostRepository repository;

    /**
    * 一覧を表示する。
    *
    * @param model モデル
    * @return テンプレート
    */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("form", PostFactory.newPost());
        model = this.setList(model);
        model.addAttribute("path","create");
        return "layout";
    }
    
    /**
     * 一覧を設定する。
     * 
     * @param model　モデル
     * @return 一覧を設定したモデル
     */
    private Model setList(Model model){
        
        //更新日時の新しい順に並び替え
        //Iterable<Post> list = repository.findAllByOrderByUpdatedDateDesc();
        //Iterable<Post> list = repository.findAll(Sort.by(Sort.Direction.DESC,"updateDate"));
        
        Iterable<Post> list = repository.findByDeletedFalseOrderByUpdatedDateDesc();
        model.addAttribute("list",list);
        return model;
    }
    
    /**
     * 登録する。
     * 
     * @param form フォーム
     * @param model モデル
     * @return テンプレート
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String cleate(@ModelAttribute("form") @Validated(GroupOrder.class) Post form, BindingResult result, Model model) {
        if(!result.hasErrors()) {
            repository.saveAndFlush(PostFactory.createPost(form));
            model.addAttribute("form",PostFactory.newPost());
        }
        model = this.setList(model);
        model = model.addAttribute("path","create");
        return "layout";
    }
    
    /**
     * 編集する内容を表示する
     * 
     * @param form　フォーム
     * @param model　モデル
     * @return　テンプレート
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(@ModelAttribute("form") Post form,Model model) {
        Optional<Post> post = repository.findById(form.getId());
        model.addAttribute("form",post.get());
        model = this.setList(model);
        model.addAttribute("path", "update");
        return "layout";
    }
    
    /**
     * 更新
     * 
     * @param form　フォーム　
     * @param model　モデル
     * @return　テンプレート
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@ModelAttribute("form") @Validated(GroupOrder.class) Post form, BindingResult result, Model model) {
        if(!result.hasErrors()) {
            Optional<Post> post = repository.findById(form.getId());
            repository.saveAndFlush(PostFactory.updatePost(post.get(), form));
        }
        model.addAttribute("form", PostFactory.newPost());
        model = this.setList(model);
        model.addAttribute("path", "create");
        return "layout";     
    }
    
    /**
     * 削除する
     * 
     * @param form　フォーム
     * @param model　モデル
     * @return　テンプレート
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@ModelAttribute("form") Post form,Model model) {
        Optional<Post> post = repository.findById(form.getId());
        repository.saveAndFlush(PostFactory.deletePost(post.get()));
        model.addAttribute("form", PostFactory.newPost());
        model = setList(model);
        model.addAttribute("path", "create");
        return "layout";
    }
} 