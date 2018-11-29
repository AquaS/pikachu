package com.oee.pikachu.domain.VO;

import com.oee.pikachu.domain.Entitys;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Aqua on 2018/11/10.
 */
@Data
public class VoteRequestVO extends Entitys implements Serializable {

    private static final long serialVersionUID = 7059617865654128579L;

    @NotBlank
    private String openId;

    @NotNull
    private Long voteInfoId;

    @NotNull
    private Long voteDetailId;
}
