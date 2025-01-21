package com.kma.corpomation.domain.user.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String accessToken;

    @Column(nullable = false)
    private String refreshToken;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Token(User user, String accessToken, String refreshToken) {
        Assert.notNull(user, "유저는 필수입니다.");
        Assert.hasText(accessToken, "Access Token은 필수입니다.");
        Assert.hasText(refreshToken, "Refresh Token은 필수입니다.");
        this.user = user;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
