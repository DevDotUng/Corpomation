package com.kma.corpomation.domain.admin.entity;

import com.kma.corpomation.domain.user.entity.User;
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
public class BusinessFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String manager;

    @Column(nullable = false)
    private String business;

    @Column(nullable = false)
    private String fileUrl;

    @CreationTimestamp
    private LocalDateTime createAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public BusinessFile(final String manager, final String business, final String fileUrl) {
        Assert.hasText(manager, "담당자는 필수입니다.");
        Assert.hasText(business, "사업명은 필수입니다.");
        Assert.notNull(fileUrl, "파일 Url은 필수입니다.");
        this.manager = manager;
        this.business = business;
        this.fileUrl = fileUrl;
    }
}
