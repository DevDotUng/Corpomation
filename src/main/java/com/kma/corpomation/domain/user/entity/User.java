package com.kma.corpomation.domain.user.entity;

import com.kma.corpomation.domain.user.dto.UserRole;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @CreationTimestamp
    private LocalDateTime createAt;

    public User(final String username, final String email, final String password, final UserRole userRole) {
        Assert.hasText(username, "이름은 필수입니다.");
        Assert.hasText(email, "이메일은 필수입니다.");
        Assert.hasText(password, "비밀번호는 필수입니다.");
        Assert.hasText(userRole.value(), "유저 권한은 필수입니다.");
        this.username = username;
        this.email = email;
        this.password = password;
        this.userRole = userRole;
    }
}
