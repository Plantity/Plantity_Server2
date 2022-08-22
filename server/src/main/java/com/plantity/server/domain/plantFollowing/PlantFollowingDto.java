package com.plantity.server.domain.plantFollowing;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlantFollowingDto {
    private Long userId;
    private String plantIdx;
}
