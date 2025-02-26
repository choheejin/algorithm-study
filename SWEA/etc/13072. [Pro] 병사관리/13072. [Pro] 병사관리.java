import java.util.HashMap;

class UserSolution
{
	class Soldier {
		int mId, mScore;
		Soldier preSoldier;
		Soldier nxtSoldier;
		
		public Soldier(int mId, int mScore, Soldier preSoldier, Soldier nxtSoldier) {
			this.mId = mId;
			this.mScore = mScore;
			this.preSoldier = preSoldier;
			this.nxtSoldier = nxtSoldier;
		}
	}

	class LinkedList {
		Soldier head;

		public LinkedList() {
			this.head = null;
		}
		
		public void insert(int mId, int mScore) {
			if(head == null) {
				head = new Soldier(mId, mScore, null, null);
				return;
			}
			
			head = new Soldier(mId, mScore, null, head);
			head.nxtSoldier.preSoldier = head;
		}
		
		public void delete(int mId) {
			if(head.mId == mId) {
				head = head.nxtSoldier;
				return;
			}
			
			for(Soldier currNode = head; currNode != null; currNode=currNode.nxtSoldier) {
				// tail의 경우 
				if(currNode.mId == mId && currNode.nxtSoldier == null) {
					currNode.preSoldier.nxtSoldier = null;
					return;
				}
				if(currNode.mId == mId) {
					currNode.preSoldier.nxtSoldier = currNode.nxtSoldier;
					currNode.nxtSoldier.preSoldier = currNode.preSoldier; 
				}
			}
		}
		
		public void update(int mId, int mScore) {
			for(Soldier currSoldier = head; currSoldier != null; currSoldier=currSoldier.nxtSoldier) {
				if(currSoldier.mId == mId) {
					currSoldier.mScore = mScore;
					return;
				}
			}
		}
		
		public void updateAll(int mChangeScore) {
			for(Soldier currSoldier = head; currSoldier != null; currSoldier=currSoldier.nxtSoldier) {
				if(currSoldier.mScore + mChangeScore > 5) {
					currSoldier.mScore = 5;
				}
				else if(currSoldier.mScore + mChangeScore < 1) {
					currSoldier.mScore = 1;
				}
				else {
					currSoldier.mScore += mChangeScore;
				}
			}
		}
		
		public int best() {
			int bestScore = Integer.MIN_VALUE;
			int bestMid = Integer.MIN_VALUE;
			
			for(Soldier currSoldier = head; currSoldier != null; currSoldier=currSoldier.nxtSoldier) {
				if((bestScore == currSoldier.mScore && bestMid < currSoldier.mId) 
						|| bestScore < currSoldier.mScore) {
					bestMid = currSoldier.mId;
					bestScore = currSoldier.mScore; 
				}	
			}
			
			return bestMid;
		}
	}
	
	HashMap<Integer, Integer> midWithTeam;
	LinkedList[] list;
	
	public void init()
	{
		this.midWithTeam = new HashMap<>();
		this.list = new LinkedList[6];
		
		for(int i = 0; i < 6; i++) {
			list[i] = new LinkedList();
		}
	}
	
	public void hire(int mID, int mTeam, int mScore)
	{
		midWithTeam.put(mID, mTeam);
		list[mTeam].insert(mID, mScore);
	}
	
	public void fire(int mID)
	{
		int mTeam = midWithTeam.get(mID);
		list[mTeam].delete(mID);
		midWithTeam.remove(mID);
	}

	public void updateSoldier(int mID, int mScore)
	{
		int mTeam = midWithTeam.get(mID);
		list[mTeam].update(mID, mScore);
	}

	public void updateTeam(int mTeam, int mChangeScore)
	{
		list[mTeam].updateAll(mChangeScore);
	}
	
	public int bestSoldier(int mTeam)
	{
		return list[mTeam].best();
	}
}
