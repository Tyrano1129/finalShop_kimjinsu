파일 _main 실행
===

controller
===
###### MallController - 실행시 초기값 설정 및 Map 을 사용하여 여러메뉴들을 사용하기위한 공간

DAO : 사용되는 여러 데이터 저장,로드,수정,삭제,추가 등 사용되는 공간
==========
##### BoardDAO 
##### CartDAO
##### FileDAO 
##### ItemDAO
##### MemberDAO

DTO : 하나의 데이터 를 담는 공간
===
##### Board
##### Cart
##### Item
##### Member

_AdminMain : 관리자공간이며 여러 유저데이터를 관리한다.
===
AdminBoard
---
###### AdminBoard 게시글의데이터 출력해서 볼수있으며 삭제도 가능합니다.
AdminItem
---
###### AdminItem 기본적으로 출력되는 순서는 카테고리 기준으로 출력되며 아이템 추가 및 삭제가 가능 또한 
######  총 매출 아이템의 갯수높은 순으로 출력해서 확인이 가능하다.
AdminMember
---
###### AdminMember Member 데이터를 전체적으로 관리하며 admin빼고는 어떤 member는 삭제가 가능하며 member가 누가있는지 확인가능하다.
