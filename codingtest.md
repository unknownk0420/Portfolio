# 유형
해시맵 / 문자열 / 빈도수

# 왜 해시맵인가
이름이 문자열이고, 동명이인이 존재 → 단순 비교 불가  
→ 각 이름의 등장 횟수를 O(N)에 관리해야 함

# 핵심 아이디어
참가자 +1, 완주자 -1을 하면  
완주하지 못한 사람만 값이 1로 남음

# 처음 접근 (틀린 이유)
참가자 / 완주자 각각 해시맵 생성 후 비교하려 함

# 왜 틀렸나
두 맵 비교는 추가 순회 필요 → 구현 복잡  
차이만 남기는 구조를 못 만듦
# 공부법
평일에 2문제씩 풀고 있고 주말에는 실전 연습을 하고 문제를 풀고 그 다음날에 복습함 한 문제당 30분 고민하고 안되면 답지 보기

# 구현 팁
```java
Map<String, Integer> map = new HashMap<>();
for(String name : participant){
    map.put(name, map.getOrDefault(name,0)+1);
}
for(String name : completion){
    map.put(name, map.getOrDefault(name,0)-1);
}
for(Map.Entry<String, Integer> entry : map.entrySet()){
    if(entry.getValue()==1){
        return entry.getKey();
    }
}
