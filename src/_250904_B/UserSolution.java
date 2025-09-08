package _250904_B;

/**
 * 도시는 N*N사이즈, 택시는 M개, 각 칸에 있는 수들은 그 칸에 있는 택시 번호를 의미, 택시호출범위 L은 (N/10)
 * 손님의 호출(출발지로부터의 거리가 L이하인 택시들 중 호출, L내 없다면 실패)
 * 거리순 -> 이때, 택시들 중 번호가 가장 작은 택시가 호출을 받는다.
 * 두 인덱스 거리는 맨하튼거리
 * 목적지에 도달한 택시는 다른 손님 호출 전까지 이동 불가
 */

class UserSolution
{
	/**
	 * 
	 * @param N
	 * @param M
	 * @param L
	 * @param mXs
	 * @param mYs
	 */
	public void init(int N, int M, int L, int[] mXs, int[] mYs)
	{
		
	}

	public int pickup(int mSX, int mSY, int mEX, int mEY)
	{
		return -1;
	}

	public Solution.Result reset(int mNo)
	{
		Solution.Result res = new Solution.Result();

		return res;
	}

	public void getBest(int[] mNos)
	{
		return;
	}
}