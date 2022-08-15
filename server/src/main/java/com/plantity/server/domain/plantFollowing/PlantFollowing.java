package com.plantity.server.domain.plantFollowing;

import lombok.Getter;
import lombok.NonNull;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="plantFollowing")
@Getter
public class PlantFollowing {

    //로그인한 상태에서 좋아요 버튼을 누르면 좋아요 테이블에 (식물 id + 유저 id) 레코드가 생성됨
    //좋아요 버튼을 한 번 더 누르면 좋아요가 삭제됨

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(name="plant_id")
    @NonNull
    private Long plantId; // 찜한 식물 버호

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private Users user; // 유저번호


    public void add() {
    }
}
