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

//�G���[���b�Z�[�W�̎擾�ŗ��p
//import org.springframework.validation.ObjectError;



/**
 * �f���̃t�����g�R���g���[���[.
 */
@Controller
public class BoardController {
    
    /**�@���e�̈ꗗ�@*/
    @Autowired
    private PostRepository repository;

    /**
    * �ꗗ��\������B
    *
    * @param model ���f��
    * @return �e���v���[�g
    */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("form", PostFactory.newPost());
        model = this.setList(model);
        model.addAttribute("path","create");
        return "layout";
    }
    
    /**
     * �ꗗ��ݒ肷��B
     * 
     * @param model�@���f��
     * @return �ꗗ��ݒ肵�����f��
     */
    private Model setList(Model model){
        
        //�X�V�����̐V�������ɕ��ёւ�
        //Iterable<Post> list = repository.findAllByOrderByUpdatedDateDesc();
        //Iterable<Post> list = repository.findAll(Sort.by(Sort.Direction.DESC,"updateDate"));
        
        Iterable<Post> list = repository.findByDeletedFalseOrderByUpdatedDateDesc();
        model.addAttribute("list",list);
        return model;
    }
    
    /**
     * �o�^����B
     * 
     * @param form �t�H�[��
     * @param model ���f��
     * @return �e���v���[�g
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
     * �ҏW������e��\������
     * 
     * @param form�@�t�H�[��
     * @param model�@���f��
     * @return�@�e���v���[�g
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
     * �X�V
     * 
     * @param form�@�t�H�[���@
     * @param model�@���f��
     * @return�@�e���v���[�g
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
     * �폜����
     * 
     * @param form�@�t�H�[��
     * @param model�@���f��
     * @return�@�e���v���[�g
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