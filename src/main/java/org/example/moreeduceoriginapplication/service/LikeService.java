package org.example.moreeduceoriginapplication.service;

import org.example.moreeduceoriginapplication.dto.LikeDto;
import org.example.moreeduceoriginapplication.model.Like;
import org.example.moreeduceoriginapplication.model.Result;
import org.example.moreeduceoriginapplication.repository.LikeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeService {

    @Autowired
    LikeRepo likeRepo;

    public List<Like> getAll(){
        return likeRepo.findAll();
    }

    public Like getById(Long id){
        return likeRepo.findById(id).get();
    }

    public Result create(LikeDto likeDto){
        Like like = new Like();
        like.setCount(likeDto.getCount());
        likeRepo.save(like);
        return new Result(true , "❤️");
    }

    public Result deleteLike(Long id){
        likeRepo.deleteById(id);
        return new Result(true , " ochirildi");
    }

}
