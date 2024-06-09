package com.mymusic.usermanagement.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_users")
public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @NonNull
        @Column(name = "user_id")
        private Long id;
        @NonNull
        private String username;
        @NonNull
        private String email;
        @NonNull
        private String passwordHash;
        private String description;
        private String profilePicUri;
//        @ManyToMany
//        @JoinTable(
//                name = "users_roles",
//                joinColumns = @JoinColumn(name = "user_id"),
//                inverseJoinColumns = @JoinColumn(name = "role_id"))
//        @ToString.Exclude
//        private Set<Role> roles;

        @Override
        public final boolean equals(Object o) {
                if (this == o) return true;
                if (o == null) return false;
                Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
                Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
                if (thisEffectiveClass != oEffectiveClass) return false;
                User user = (User) o;
                return Objects.equals(getId(), user.getId());
        }

        @Override
        public final int hashCode() {
                return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
        }
}
