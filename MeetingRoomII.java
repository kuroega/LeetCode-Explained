public class MeetingRoomII {
	public int minMeetingRooms(Interval[] intervals) {
		int n = intervals.length;
		int[] starts = new int[n];
		int[] ends = new int[n];
		for (int i = 0; i < n; i++) {
			starts[i] = intervals[i].start;
			ends[i] = intervals[i].end;
		}
		Arrays.sort(starts);
		Arrays.sort(ends);

		int rooms = 0, ended = 0;
		for (int s : starts) {
			if (s < ends[ended])
				rooms++;
			else 
				ended++
		}
		return rooms;
	}
}