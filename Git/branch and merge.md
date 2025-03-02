# git의 branch 기능과 merge

> 개인 공부 목적으로 생성한 레포지토리라서 코딩애플 강의 사진 파일이 포함되어 있음

## branch 기능
![image description](https://github.com/user-attachments/assets/f0ad55fa-0c02-4997-8dc1-c72637fbff5b)
git 내에서는 branch라는 기능을 활용하여 프로젝트를 복사할 수 있다.   
일반적으로 프로젝트의 크기는 매우 크고 길 것이기 때문에 갑자기 새로운 기능을 추가하거나 변경하면 위험하고 복잡하다. 그렇기 때문에 소스코드를 직접 수정하지 않고 사본을 만들어 먼저 개발하는 느낌으로 사용할 수 있다.

```
git branch 브랜치이름
git switch 브랜치이름
```
- branch 명령어를 통해 브랜치 이름을 지정하여 그 이름의 프로젝트 사본을 만든다.
- switch 명령어를 통해 해당 브랜치로 이동할 수 있다. (원래로 돌아오려면 main 혹은 master)
- 내역을 확인하고 싶으면 ```git log --graph --oneline --all``` 활용하면 된다. branch와 commit 내역이 그래프로 확인 가능하다. **git log에서 나오는 HEAD는 현재 위치를 의미한다.**

이런 브랜치들을 통해 진행했다가 기능 확인 다 했고 원래 하던데에 다 합치고 싶으면 merge를 쓴다. 브랜치를 합치는 것이다.
```
git switch main
git merge 브랜치이름
```
main 혹은 master 브랜치로 이동해서 브랜치명을 merge 명령어로 합치면 된다.

> branch 기능을 활용하면 여러 개발자들과 협업하면서 동시에 같은 소스코드를 수정하다가 저장해야할 일이 생겨도 branch로 사본을 만들어서 개발을 하고 테스트 후 잘 되면 main branch에 합치는 방식으로 하면 개개인이 저장하면서 쓰거나 날릴 가능성이 있는 것보다 훨씬 안정적인 개발이 가능하다.
<br>
<br>

## git merge의 CONFLICT
![image](https://github.com/user-attachments/assets/cca03629-59ae-45f7-9503-66e6c62486f8)
git merge를 하는데 CONFLICT (content): Merge conflict in code.txt 같은 오류가 뜰 수도 있다.   
- 에디터로 파일을 열어보면 충돌 사항이 적혀 있다.
- 대체로 브랜치를 합치려다가 보니 똑같은 곳을 각 브랜치에서 다르게 수정해서 선택해야 하는 경우다.
- 그래서 어떤 걸 고를지 확인하고, 다른 것은 다 지우고 원하는 코드만 남긴 상태로 버튼 누르면 된다.
- 어떤 코드를 남길지 결정했다면 ```git add 파일명``` , ```git commit -m '메세지'``` 입력해서 새 commit 생성하면 된다.
<br>
<br>

## git에서 merge의 방식
![image](https://github.com/user-attachments/assets/d0534cf0-8c45-4e70-b90e-1a951fd07f19)
1. **3-Way Merge**   
가장 기본이 되는 방식이고, 새로운 commit을 만든 상태에서 commit 2-2와 commit 3을 합치는 방식으로 작동한다.
- 이 방식은 기본적으로 굉장히 복잡하게 보인다. 프로젝트 파일이 여러개라면 읽기도 보기도 힘들다.
- main 브랜치의 git log를 출력하면 3-way merge 된 브랜치들의 commit 내역까지 다 보여서 더러워진다.
- 심지어, 브랜치 merge를 해도 브랜치가 **자동으로 삭제되는 것이 아니다** 보니 그거까지 다 나오면 매우 불편해진다.
- 아래를 직접 보면 어질어질해진다...
![image](https://github.com/user-attachments/assets/4816811f-4110-4f1d-a0f1-5214ae61685b)
<br>

![image](https://github.com/user-attachments/assets/46d89c00-6c88-48a0-a5cf-58120bf5f793)
2. **Fast-forward Merge**
merge를 하면 fast-forward merge를 진행했다고 알려주게 된다.
- 새로 브랜치를 짜서 추가하고 했는데 main에는 특별히 변동이 뭐가 없는 경우
- 굳이 새로 합치기보다, 신규 브랜치를 보고 넌 이제 main 브랜치다 하고 지정해주는 것
- **기준 브랜치에 신규 commit이 없는** 경우에 자동으로 발동되는 것이다.
- 싫으면 ```git merge --no-ff 브랜치명``` 활용하면 강제 3-way merge가 가능하다. 근데 굳이 할려나?
<br>

![image](https://github.com/user-attachments/assets/466d9483-7649-49b5-844a-8fe8caa0ce5b)
3. **Rebase and Merge**
브랜치를 rebase한다는 것은, **다른 commit을 시작점으로 옮기는** 것이다.
- 가지를 잘라서 다른 곳에 옮겨 심어주는 것으로 보면 된다. 가령, 위 사진은 파란 가지(commit2-1 부분)를 commit3으로 옮긴 격이다.
- 그렇게 하면 이미 main에는 변동사항이 없으니, fast-forward Merge가 가능해진다.
- 새로운 브랜치로 이동하고, ```git rebase main``` 해주면 main 브랜치 끝으로 브랜치가 이동한다.
- 그리고 나서 ```git switch main``` 한 다음 ```git merge 새브랜치이름``` 사용해주면 fast-forward merge 된다.

> 왜 쓰는걸까 하면, 강제로 fast-forward를 사용하고 싶거나, commit 내역이 너무 지저분하게 되지 않게 한 줄로 계속 이어서 남기고 싶을 때 사용한다. <br>
  물론 얘도 단점은 있다. 브랜치끼리 차이가 너무 많이 나면 rebase 시 위에처럼 conflict가 발생한다... 해결이 번거로워질 수 있다.

![image](https://github.com/user-attachments/assets/141bd1f9-e7c0-4ca0-8412-6a9c798f5731)
4. **Squash and Merge**
이건, 3-way merge 방식에서 선으로 다 이어주는 것이 아니라, 새 브랜치의 **변경사항들을 main 브랜치로 모두 옮겨주는** 방식이다.
- main 브랜치의 git log를 출력하면 merge 완료된 브랜치의 commit 같은 것들은 출력되지 않는다.
- 하는 방법은 간단한데, ```git merge --squash 브랜치명`` 처럼 **--squash 옵션을 추가** 해주면 된다.
- 브랜치에서 만든 많은 commit들을 다 합쳐서 하나의 commit으로 만들어 주는 것이다.

> git log 그래프가 나중에 복잡해지는 것을 방지하기 위해 일반 merge 대신 사용하는 것이다. 브랜치 개수 많은데 그냥 다 3-way로 박아버리면 언젠간 어질어질해진다. rebase나 squash 사용하자.




