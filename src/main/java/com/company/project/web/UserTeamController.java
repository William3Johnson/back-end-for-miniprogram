package com.company.project.web;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.UserTeam;
import com.company.project.service.UserTeamService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by CodeGenerator on 2019/05/25.
*/
@RestController
@RequestMapping("/user/team")
public class UserTeamController {
    @Resource
    private UserTeamService userTeamService;

    @PostMapping("/add")
    public Result add(@RequestBody UserTeam userTeam) {
        userTeamService.addUserToTeam(userTeam);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(String username,int teamId) {
        userTeamService.removeUserFromTeam(username,teamId);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(UserTeam userTeam) {
        userTeamService.update(userTeam);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        UserTeam userTeam = userTeamService.findById(id);
        return ResultGenerator.genSuccessResult(userTeam);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<UserTeam> list = userTeamService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
