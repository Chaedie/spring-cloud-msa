# User Service

### Service 단에서 직접 UUID 를 생성하는 이유?

```java

@Service
public class UserServiceImpl implements UserService {

    @Override
    public UserDto create(UserDto userDto) {
        // UUID 를 생성하는 이유는??
        userDto.setUserId(UUID.randomUUID().toString());

        return null;
    }
}
```

- MSA 구조에서 ID 충돌 없이 여러 인스턴스에서 생성하기 위함
    - 예: 여러 서비스에서 동시에 유저를 생성하는 상황
    - DB 트랜잭션 없이도 각 인스턴스에서 안전하게 고유 ID 생성 가능

### Response 에서 JsonInclude 어노테이션이 무엇인가?

ex) `@JsonInclude(JsonInclude.Include.NON_NULL)`

- JSON 직렬화 시 NULL 은 포함되지 않도록 하는 어노테이션
- 종류
  ```java
  @JsonInclude(JsonInclude.Include.ALWAYS)      // (기본값) null도 포함
  @JsonInclude(JsonInclude.Include.NON_NULL)    // null은 제외
  @JsonInclude(JsonInclude.Include.NON_EMPTY)   // null, ""(빈문자), 빈 리스트 다 제외
  @JsonInclude(JsonInclude.Include.NON_DEFAULT) // default 값이면 제외
  ```
    