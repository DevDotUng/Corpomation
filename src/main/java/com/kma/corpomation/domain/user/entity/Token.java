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

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id")
    private User user;

    public Token(String accessToken, String refreshToken) {
        Assert.hasText(accessToken, "Access Token은 필수입니다.");
        Assert.hasText(refreshToken, "Refresh Token은 필수입니다.");
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
