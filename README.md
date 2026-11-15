# Java 2048 — 하루 실습용 스타터

## 무엇이 완성되어 있나요?
- Swing 창 및 4×4 타일 렌더링
- 2/4 등 타일 색상과 중앙 정렬 숫자
- 방향키/WASD 입력 연결, R 재시작
- Gradle 빌드 및 JUnit 테스트 기본 환경

## 무엇을 직접 구현하나요?
`src/main/java/com/example/game2048/Game2048.java`의 `move(Direction direction)`
1. 이동 방향에 따라 배열의 행 또는 열을 읽는다.
2. 빈 칸을 무시하고 숫자를 앞으로 압축한다.
3. 인접한 같은 숫자를 한 번만 합친다.
4. 이동 결과가 기존 상태와 다를 때만 랜덤 2 또는 4를 생성한다.
5. 점수와 승리/패배를 관리한다.
6. 고정 예시 타일을 실제 랜덤 초기화로 교체한다.

**주의:** 현재 방향키는 인식되지만, 아직 타일은 움직이지 않습니다.
로직을 스스로 구현하는 과제이므로 의도적으로 비워 놓았습니다.

## Windows IntelliJ IDEA 실행
1. 프로젝트 폴더를 압축 해제합니다.
2. IntelliJ IDEA -> Open -> `java-2048-starter` 폴더 선택
3. JDK 21 선택. Gradle 프로젝트로 인식하면 동기화 (최초 의존성 다운로드에는 인터넷 필요).
4. `src/main/java/.../Main.java` 열기 -> 초록색 실행 버튼

### 별도 Gradle 설치 없이 빠르게 실행
- Windows에서 JDK 21 `javac`, `java`가 PATH에 있다면 `run-windows.bat`을 실행하세요.
- 배치 파일은 JUnit 실행 없이 메인 코드를 컴파일하고 실행합니다.

### 터미널에서 실행
- Gradle이 설치된 환경이라면: `gradle run` / `gradle test`
- 이 ZIP에는 Gradle wrapper(gradlew)가 포함되지 않았습니다. IntelliJ는 Gradle을 다운로드/설정할 수 있습니다.
- 새 프로젝트 마법사에서 Gradle wrapper를 생성해 추가해도 됩니다.

## 구성
```text
src/main/java/com/example/game2048/
  Main.java        # Swing 이벤트 디스패치 스레드에서 창 실행
  Game2048.java    # 게임 규칙과 상태, 직접 구현할 부분
  Direction.java   # 방향 enum
  BoardPanel.java  # paintComponent()에서 타일 그리기
  GameWindow.java  # JFrame, 점수 표시, 키 입력 연결
src/test/java/com/example/game2048/
  Game2048Test.java # 기초 테스트 + TODO
```

## 왜 이런 분리인가요?
- Game2048은 Swing을 몰라도 동작하므로 콘솔이나 웹 UI로도 재사용 가능합니다.
- BoardPanel은 그리기만 담당합니다. 게임 규칙을 몰아넣지 마세요.
- GameWindow는 사용자의 행동을 게임 로직에 전달하고 다시 그리게 합니다.
- JUnit으로 게임 규칙만 빠르게 검증할 수 있습니다.

## 제안 테스트
- `[2,2,2,2]` 왼쪽 -> `[4,4,0,0]`
- `[2,0,2,2]` 왼쪽 -> `[4,2,0,0]`
- `[2,4,8,16]` 왼쪽 -> 변화 없음 (랜덤 타일 생성 X)
- 마지막 타일이 합쳐지는 경우 / 더는 움직일 수 없는 경우

## 기술 선택
- Java 21 / Swing(자바 기본) / Graphics2D
- Gradle(`application`) / JUnit 5.13.4
- Gradle을 JDK 21로 실행하려면 Gradle 8.5 이상이 필요합니다.
