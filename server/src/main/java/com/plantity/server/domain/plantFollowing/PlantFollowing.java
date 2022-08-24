package com.plantity.server.domain.plantFollowing;

import com.plantity.server.domain.BaseTimeEntity;
import com.plantity.server.domain.plant.detail.PlantDetail;
import com.plantity.server.domain.users.Users;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name="plantFollowing")
@Getter
public class PlantFollowing extends BaseTimeEntity {

    //로그인한 상태에서 좋아요 버튼을 누르면 좋아요 테이블에 (식물 id + 유저 id) 레코드가 생성됨
    //좋아요 버튼을 한 번 더 누르면 좋아요가 삭제됨

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long plantFollowingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="plantIdx")
    private PlantDetail plant; // 찜한 식물 번호

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userId")
    private Users users; // 유저번호

}
