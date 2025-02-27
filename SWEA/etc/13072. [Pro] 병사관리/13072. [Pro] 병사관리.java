class UserSolution
{
	int[] version = new int[100001];
	int[] midWithTeam = new int[100001];
	Team[] teams = new Team[6];
	
	// 같은 팀 내에 점수별로 나뉘는거
	class Team {
		ScoreGroup[] group = new ScoreGroup[6];;
		
		public Team() {
			for(int i = 1; i <= 5; i++) {
				group[i] = new ScoreGroup();
			}
		}
		
		public void insert(int mid, int mScore) {
			group[mScore].insert(mid, mScore, ++version[mid]);
		}
		
		public void delete(int mid) {
			version[mid] = -1;
		}
		
		public void update(int mid, int mScore) {
			this.insert(mid, mScore);
		}
		
		// 뭔가 구현이 요상한데 ㅠ 
		public void updateAll(int mChangeScore) {
		    if(mChangeScore == 0) return;
		    
		    // 점수 감소의 경우 (낮은 점수부터 처리해야 함)
		    if(mChangeScore < 0) {
		        for(int j = 1; j <= 5; j++) {
		            // 변경된 점수 계산 (1~5 범위 내에서 제한)
		            int k = j + mChangeScore;
		            k = k < 1 ? 1 : (k > 5 ? 5 : k);
		            
		            // 같은 점수면 변경 필요 없음
		            if(j == k) continue;
		            
		            // 해당 점수 그룹에 군인이 없으면 건너뛰기
		            if(group[j].head == null) continue;
		            
		            // 대상 점수 그룹에 군인 추가
		            if(group[k].head == null) {
		                group[k].head = group[j].head;
		                group[k].tail = group[j].tail;
		            } else {
		                group[k].tail.next = group[j].head;
		                group[k].tail = group[j].tail;
		            }
		            
		            // 원래 점수 그룹 초기화
		            group[j].head = null;
		            group[j].tail = null;
		        }
		    }
		    // 점수 증가의 경우 (높은 점수부터 처리해야 함)
		    else {
		        for(int j = 5; j >= 1; j--) {
		            // 변경된 점수 계산 (1~5 범위 내에서 제한)
		            int k = j + mChangeScore;
		            k = k < 1 ? 1 : (k > 5 ? 5 : k);
		            
		            // 같은 점수면 변경 필요 없음
		            if(j == k) continue;
		            
		            // 해당 점수 그룹에 군인이 없으면 건너뛰기
		            if(group[j].head == null) continue;
		            
		            // 대상 점수 그룹에 군인 추가
		            if(group[k].head == null) {
		                group[k].head = group[j].head;
		                group[k].tail = group[j].tail;
		            } else {
		                group[k].tail.next = group[j].head;
		                group[k].tail = group[j].tail;
		            }
		            
		            // 원래 점수 그룹 초기화
		            group[j].head = null;
		            group[j].tail = null;
		        }
		    }
		}		
		public int bestScore() {
			int bestScore = Integer.MIN_VALUE;
			int bestMid = Integer.MIN_VALUE;
			
			for(int i = 5; i > 0; i--) {
				// 최고점수 이미 찾음
				if(bestScore > i) break;
				
				if(group[i] != null) {
					for(Solider curr = group[i].head; curr != null ; curr = curr.next) {
						if(curr.version == version[curr.mid]) {
							if(bestScore < i) {
								bestScore = i;
								bestMid = curr.mid;
							}
							else if(bestScore == i) {
								bestMid = Math.max(curr.mid, bestMid);
							}
						}
					}
				}
			}
			return bestMid;
		}
	}
	
	class ScoreGroup {
		Solider head, tail;
		
		public ScoreGroup() {
			this.head = null;
			this.tail = null;
		}
		
		public void insert(int mid, int score, int version) {
			if(head == null) {
				head = new Solider(mid, score, version, null);
				tail = head;
				return;
			}
			
			tail.next = new Solider(mid, score, version, null);
			tail = tail.next;
		}
	}
	
	
	class Solider {
		int score, mid, version;
		Solider next;
		public Solider(int mid, int score, int version, Solider next) {
			this.mid = mid;
			this.score = score;
			this.version = version;
			this.next = next;
		}
	}
	
	public void init()
	{
		for(int i = 1; i <= 5; i++) {
			teams[i] = new Team();
		}
	}
	
	public void hire(int mID, int mTeam, int mScore)
	{
		midWithTeam[mID] = mTeam;
		teams[mTeam].insert(mID, mScore);
	}
	
	public void fire(int mID)
	{
		int mTeam = midWithTeam[mID];
		teams[mTeam].delete(mID);
	}

	public void updateSoldier(int mID, int mScore)
	{
		int mTeam = midWithTeam[mID];
		teams[mTeam].update(mID, mScore);
	}

	public void updateTeam(int mTeam, int mChangeScore)
	{
		teams[mTeam].updateAll(mChangeScore);
	}
	
	public int bestSoldier(int mTeam)
	{
		return teams[mTeam].bestScore();
	}
}
