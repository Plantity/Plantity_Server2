package com.plantity.server.domain;


import lombok.*;

@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlantFollowingDto {
    private Long plantId;
    private static Long userId;

    public static Long getUserId(){
        return userId;
    }
}
