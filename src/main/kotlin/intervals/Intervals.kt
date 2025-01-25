package intervals

import annotations.Important
import annotations.Revise
import java.util.*

/**
 * https://docs.google.com/document/d/1rUyk-1pioNOZvh9gp7lPbDbm0G6cEKP0QlyrlrvVQhY/edit
 */
class Intervals {

    fun areIntervalsOverlapping(interval1: IntArray, interval2: IntArray): Boolean {
        // Extracting start and end points of intervals
        //  [4,8] [1,5] - true | [3,4] [1,2] - false
        val (A, B) = interval1
        val (C, D) = interval2

        // Checking for overlap for inclusive intervals
//        return A <= D && B >= C
        // Checking for overlap for exclusive intervals
        return A < D && B > C
    }

    /**
     * Given an array of intervals where intervals[i] = [starti, endi],
     * merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.
     *     Example 1:
     *
     *     Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
     *     Output: [[1,6],[8,10],[15,18]]
     *     Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
     *     Example 2:
     *
     *     Input: intervals = [[1,4],[0,5]]
     *     Output: [[1,4]]
     *     Explanation: intervals.Intervals [1,4] and [0,5] are considered overlapping.
     */


    fun mergeIntervals(list: Array<IntArray>): Array<IntArray> {
        // if given sorted intervals
        // overlap condition  B>=C
        // mergedInterval = [A, maxOf(B,D)]

        //Sort according to their starts.
        // And while merging just check
        // if the prevEnd >= currStart
        // If yes set end = max(firstListEnd , secondListEnd)
        // else the intervals are disjoint hence simply add the current
        // Interval without making any changes to the previous interval.

        if (list.size < 2) return list
        val result = mutableListOf<IntArray>()
        list.sortBy { it[0] }

        var prev = list[0]

        for (curr in list) {
            if (prev[1] >= curr[0]) {
                prev[1] = maxOf(prev[1], curr[1])
            } else {
                result.add(intArrayOf(prev[0], prev[1]))
                prev = curr
            }
        }

        result.add(prev)
        return result.toTypedArray()
    }


    /**
     * You are given an array of non-overlapping intervals
     * where intervals[i] = [starti, endi] represent the start and the end of the ith interval and
     * intervals is sorted in ascending order by starti.
     * You are also given an interval newInterval = [start, end] that represents the start and end of another interval.
     *
     * Insert newInterval into intervals such that intervals is still sorted in ascending order by starti
     * and intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).
     *
     * Return intervals after the insertion.
     *
     * Note that you don't need to modify intervals in-place. You can make a new array and return it.
     *
     *
     *
     * Example 1:
     *
     * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
     * Output: [[1,5],[6,9]]
     * Example 2:
     *
     * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
     * Output: [[1,2],[3,10],[12,16]]
     * Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
     */
    fun insertIntervals(intervals: Array<IntArray>, newInterval1: IntArray): Array<IntArray> {
        /*
        1. check for the intervals.end < newInterval.start and add to res
        2. check for overlapping newInterval.end > intervals.start and merge the intervals start = minOf() , end = maxOf()
        4. add the merged newInterval to res
        3. check for remaining and add to res
         */
        val result = mutableListOf<IntArray>()
        val newInterval = newInterval1
        var i = 0

        //add all the non merging intervals before newInterval
        while (i < intervals.size && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i])
            i++
        }
        // merge the overlapping intervals to single one
        while (i < intervals.size && newInterval[1] >= intervals[i][0]) {
            newInterval[0] = minOf(newInterval[0], intervals[i][0])
            newInterval[1] = maxOf(newInterval[1], intervals[i][1])
            i++
        }
        result.add(newInterval)

        //remaining intervals we can club into one for loop
        while (i < intervals.size) {
            result.add(intervals[i])
            i++
        }

        return result.toTypedArray()
    }

    /**
     * Klee algorithm : helps to find the union of the line segments
     *
     * Union of Line Segments is a problem where we are given a list of line segments
     * and we have to find the length of the union of the line segments.
     * Example 1:
     * Input: intervals = [[1,4],[3,6],[2,8]]
     * Output: 7
     *
     *
     * Example 2:
     * Input: intervals = [[5,8],[3,6],[2,4]]
     * Output: 6
     *
     *
     * Intervals:
     * [1, 4]  :  |---|
     * [3, 6]  :     |---|
     * [2, 8]  :   |-------|
     *
     * Union of Intervals:
     * [1, 8]  :  |-------|
     *
     */
    @Important
    fun maxLineSegmentLength(intervals: Array<IntArray>): Int {
        /*
        It is used to find the length of the union of the line segments.
        1. create list with items (start,false) and (end,true)
        2. sort the list by first
        3. iterate and check for overlap and count >0 if overlap then add the length to result
        4. if end then decrement the count else increment the count
        5. if count is zero, then make result = 0
         */
        var result = 0
        var count = 0

        val list = mutableListOf<Pair<Int, Boolean>>()

        for (item in intervals) {
            list.add(Pair(item[0], false))
            list.add(Pair(item[1], true))
        }

        list.sortBy { it.first }

        for (i in list.indices) {
            if (count > 0 && (i - 1) in list.indices) {
                result = maxOf(result, result + list[i].first - list[i - 1].first)
            }

            if (list[i].second)
                count--
            else
                count++

            if (count == 0) result = 0
        }

        return result
    }

    /**
     * There are some spherical balloons taped onto a flat wall that represents the XY-plane.
     * The balloons are represented as a 2D integer array points where points[i] = [xstart, xend]
     * denotes a balloon whose horizontal diameter stretches between xstart and xend. You do not know the exact y-coordinates of the balloons.
     * Arrows can be shot up directly vertically (in the positive y-direction) from different points along the x-axis.
     *
     * A balloon with xstart and xend is burst by an arrow shot at x if xstart <= x <= xend.
     * There is no limit to the number of arrows that can be shot. A shot arrow keeps traveling up infinitely, bursting any balloons in its path.
     * Given the array points, return the minimum number of arrows that must be shot to burst all balloons.
     *
     *
     * Example 1:
     *
     * Input: points = [[10,16],[2,8],[1,6],[7,12]]
     * Output: 2
     * Explanation: The balloons can be burst by 2 arrows:
     * - Shoot an arrow at x = 6, bursting the balloons [2,8] and [1,6].
     * - Shoot an arrow at x = 11, bursting the balloons [10,16] and [7,12].
     * Example 2:
     *
     * Input: points = [[1,2],[3,4],[5,6],[7,8]]
     * Output: 4
     * Explanation: One arrow needs to be shot for each balloon for a total of 4 arrows.
     * Example 3:
     *
     * Input: points = [[1,2],[2,3],[3,4],[4,5]]
     * Output: 2
     * Explanation: The balloons can be burst by 2 arrows:
     * - Shoot an arrow at x = 2, bursting the balloons [1,2] and [2,3].
     * - Shoot an arrow at x = 4, bursting the balloons [3,4] and [4,5].
     */
    fun minArrowShots(points: Array<IntArray>): Int {
        /*
         1. sort points by start
         2. iterate check for overlap prev.end>curr.start
         3.  if overlaps  update prev () = maxOf(s1,s2) and minOf(e1,e2)
         4. inc count and update prev = curr
         */
        if (points.isEmpty()) return 0
        if (points.size == 1) return 1
        points.sortBy { it[0] }

        var result = 1
        var prev = points[0]
        for (i in 1..points.lastIndex) {
            val curr = points[i]
            if (prev[1] >= curr[0]) {
                prev[0] = maxOf(prev[0], curr[0])
                prev[1] = minOf(prev[1], curr[1])
            } else {
                result++
                prev = curr
            }
        }

        return result
    }

    /**
     * N meetings in one room
     * Difficulty: EasyAccuracy: 45.3%Submissions: 295K+Points: 2
     * You are given timings of n meetings in the form of (start[i], end[i]) where start[i] is the start time of meeting i and
     * end[i] is the finish time of meeting i.
     * Return the maximum number of meetings that can be accommodated in a single meeting room,
     * when only one meeting can be held in the meeting room at a particular time.
     *
     * Note: The start time of one chosen meeting can't be equal to the end time of the other chosen meeting.
     *
     * Examples :
     *
     * Input: start[] = [1, 3, 0, 5, 8, 5], end[] =  [2, 4, 6, 7, 9, 9]
     * Output: 4
     * Explanation: Maximum four meetings can be held with given start and end timings. The meetings are - (1, 2), (3, 4), (5,7) and (8,9)
     * Input: start[] = [10, 12, 20], end[] = [20, 25, 30]
     * Output: 1
     * Explanation: Only one meetings can be held with given start and end timings.
     * Input: start[] = [1, 2], end[] = [100, 99]
     * Output: 1
     * Constraints:
     * 1 ≤ n ≤ 105
     * 0 ≤ start[i] < end[i] ≤ 106
     */
    fun maxMeetings(start: IntArray, end: IntArray): Int {
        /*
        1. create a list of pairs with start and end
        2. sort the list by end
        3. iterate and check for prev.end < curr.start
        4. if true inc count and update prev = curr
         */
        val list = mutableListOf<Pair<Int, Int>>()
        for (i in start.indices) {
            list.add(Pair(start[i], end[i]))
        }

        list.sortBy { it.second }
        var result = 1
        var prev = list[0]
        for (i in 1..list.lastIndex) {
            val curr = list[i]
            if (prev.second < curr.first) {
                result++
                prev = curr
            }
        }

        return result
    }

    /**
     * Given an array of meeting time intervals consisting of start and
     * end times [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.
     * Example 1:
     * Input: [[0,30],[5,10],[15,20]]
     * Output: false
     *
     * Example 2:
     * Input: [[7,10],[2,4]]
     * Output: true
     * NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
     *
     */
    fun canAttendAllMeetings(meetings: Array<IntArray>): Boolean {
        /*
        1. sort meetings by start
        2. iterate and check for prev.end > curr.start
        3. if true return false
        4. else true
         */
        if (meetings.isEmpty()) return true
        meetings.sortBy { it[0] }
        // check for the overlap
        for (i in 1..meetings.lastIndex) {
            val prev = meetings[i - 1]
            val curr = meetings[i]
            if (prev[1] > curr[0]) return false
        }

        return true
    }


    /**
     * Description
     * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
     * find the minimum number of conference rooms required.
     **
     *
     * (0,8),(8,10) is not conflict at 8
     *
     * Example1
     *
     * Input: intervals = [(0,30),(5,10),(15,20)]
     * Output: 2
     * Explanation:
     * We need two meeting rooms
     * room1: (0,30)
     * room2: (5,10),(15,20)
     * Example2
     *
     * Input: intervals = [(2,7)]
     * Output: 1
     * Explanation:
     * Only need one meeting room
     */
    @Important
    fun minMeetingRooms(meetings: Array<IntArray>): Int {
        /*
        1. create start array and sort
        2. create end array and sort
        3. if start time is before end time then we create room and inc start time pointer
        4. else we remove a room and inc end time pointer
        5. every iteration we update the result with max of result and room count
         */

        val start = IntArray(meetings.size) { meetings[it][0] }
        val end = IntArray(meetings.size) { meetings[it][1] }

        start.sort()
        end.sort()
        //  [(0,10),(5,10),(15,20),(10,15),(10,20),(5,15)]
        //  [0,5,5,10,10,15] - start time of the meetings
        //  [10,10,15,15,20,20] - end time of the meetings
        // if start[i] < end[j] then we need a new room
        // else decrement room
        var result = 0
        var count = 0

        var i = 0
        var j = 0
        while (i < meetings.size) {
            if (start[i] < end[j]) {
                count++
                i++
            } else {
                count--
                j++
            }
            result = maxOf(result, count)
        }
//        return result

        //using priority queue
        // [(0,10),(5,10),(15,20),(10,15),(10,20),(5,15)]

        // we peek the smallest end time and check if the start time is greater than the end time
        val pq = PriorityQueue<Int>()
        meetings.sortBy { it[0] }
        for (meeting in meetings) {
            if (pq.isNotEmpty() && pq.peek() < meeting[0]) {
                pq.remove()
            }
            pq.add(meeting[1])
        }

        return pq.size
    }

    /**
     * Minimum Platforms
     * Difficulty: MediumAccuracy: 26.84%Submissions: 484K+Points: 4
     * You are given the arrival times arr[] and departure times dep[] of all trains that arrive at a railway station on the same day.
     * Your task is to determine the minimum number of platforms required at the station to ensure that no train is kept waiting.
     *
     * At any given time, the same platform cannot be used for both the arrival of one train and the departure of another.
     * Therefore, when two trains arrive at the same time, or when one arrives before another departs,
     * additional platforms are required to accommodate both trains.
     *
     * Examples:
     *
     * Input: arr[] = [900, 940, 950, 1100, 1500, 1800], dep[] = [910, 1200, 1120, 1130, 1900, 2000]
     * Output: 3
     * Explanation: There are three trains during the time 9:40 to 12:00. So we need a minimum of 3 platforms.
     * Input: arr[] = [900, 1235, 1100], dep[] = [1000, 1240, 1200]
     * Output: 1
     * Explanation: All train times are mutually exclusive. So we need only one platform
     * Input: arr[] = [1000, 935, 1100], dep[] = [1200, 1240, 1130]
     * Output: 3
     * Explanation: All 3 trains have to be there from 11:00 to 11:30
     * Constraints:
     * 1≤ number of trains ≤ 50000
     * 0000 ≤ arr[i] ≤ dep[i] ≤ 2359
     * Note: Time intervals are in the 24-hour format(HHMM) , where the first two characters represent hour (between 00 to 23 ) and the last two characters represent minutes (this will be <= 59 and >= 0).
     */
    fun minPlatforms(arr: IntArray, dep: IntArray): Int {
        /*
        1. sort the arr and dep
        2. iterate and check for arr[i] < dep[j] then inc count and inc i
        3. else dec count and inc j
        4. update the result with max of result and count
         */
        arr.sort()
        dep.sort()
        var result = 0
        var count = 0
        var i = 0
        var j = 0
        while (i < arr.size) {
            if (arr[i] < dep[j]) {
                count++
                i++
            } else {
                count--
                j++
            }
            result = maxOf(result, count)
        }

//        return result
        val pq = PriorityQueue<Int> { a, b -> a - b }
        val timings = Array(arr.size) { intArrayOf(arr[it], dep[it]) }
        timings.sortBy { it[0] }
        for (timing in timings) {
            if (pq.isNotEmpty() && pq.peek() < timing[0]) {
                pq.remove()
            }

            pq.add(timing[1])
        }

        return pq.size
    }

    /**
     *You are given an array of intervals, where intervals[i] = [starti, endi] and each starti is unique.
     *
     * The right interval for an interval i is an interval j such that startj >= endi and startj is minimized. Note that i may equal j.
     *
     * Return an array of right interval indices for each interval i. If no right interval exists for interval i, then put -1 at index i.
     *
     *
     *
     * Example 1:
     *
     * Input: intervals = [[1,2]]
     * Output: [-1]
     * Explanation: There is only one interval in the collection, so it outputs -1.
     * Example 2:
     *
     * Input: intervals = [[3,4],[2,3],[1,2]]
     * Output: [-1,0,1]
     * Explanation: There is no right interval for [3,4].
     * The right interval for [2,3] is [3,4] since start0 = 3 is the smallest start that is >= end1 = 3.
     * The right interval for [1,2] is [2,3] since start1 = 2 is the smallest start that is >= end2 = 2.
     * Example 3:
     *
     * Input: intervals = [[1,4],[2,3],[3,4]]
     * Output: [-1,2,-1]
     * Explanation: There is no right interval for [1,4] and [3,4].
     * The right interval for [2,3] is [3,4] since start2 = 3 is the smallest start that is >= end1 = 3.
     */
    fun findRightInterval(intervals: Array<IntArray>): IntArray {
        /*
        it uses Treemap which is a sorted map
        1. create a treemap and add all the start times and index
        2. iterate over intervals and find ceilingEntry of the curr.end
        3. if not null and index to res else -1

         */
        val res = IntArray(intervals.size) { -1 }
        val starts = TreeMap<Int, Int>()
        for (i in intervals.indices) {
            starts[intervals[i][0]] = i
        }


        for (i in intervals.indices) {
            starts.ceilingEntry(intervals[i][1])?.let {
                res[i] = it.value
            }
        }

        return res
    }

    /**
     * Job Sequencing Problem
     * Difficulty: MediumAccuracy: 34.51%Submissions: 266K+Points: 4
     * Given an array, jobs[] where each job[i] has a jobid, deadline and profit associated with it.
     * Each job takes 1 unit of time to complete and only one job can be scheduled at a time.
     * We earn the profit associated with a job if and only if the job is completed by its deadline.
     *
     * Find the number of jobs done and the maximum profit.
     *
     * Note: jobs will be given in the form (jobid, deadline, profit) associated with that job.
     * Deadline of the job is the time on or before which job needs to be completed to earn the profit.
     *
     * Examples :
     *
     * Input: jobs[] = [(1,4,20), (2,1,1), (3,1,40), (4,1,30)]
     * Output: [2, 60]
     * Explanation: job1 and job3 can be done with maximum profit of 60 (20+40).
     * Input: jobs[] = [(1,2,100), (2,1,19), (3,2,27), (4,1,25), (5,1,15)]
     * Output: [2, 127]
     * Explanation: 2 jobs can be done with maximum profit of 127 (100+27).
     * Input: jobs[] = [(1,3,50), (2,1,10), (3,2,20), (4,2,30)]
     * Output: [2, 80]
     * Explanation: Job 1 and Job 4 can be completed with a maximum profit of 80 (50 + 30).
     * Constraints:
     * 1 <=  jobs.size <= 105
     * 1 <= deadline, jobid <= jobs.size
     * 1 <= profit <= 500
     */
    fun jobSequencing(jobs: Array<Triple<Int, Int, Int>>): IntArray {
        /*
        1. sort the jobs by profit
        2. iterate and check for the max deadline
        3. create a slot array and count = 0, profit = 0
        4. Her slot array is used to check if the job is done or not
        5. iterate and check for the max deadline

         */
        jobs.sortBy { -it.third }
        var maxDeadline = Int.MIN_VALUE
        for (job in jobs) {
            maxDeadline = maxOf(maxDeadline, job.second)
        }
        val slot = BooleanArray(maxDeadline + 1)
        var count = 0
        var profit = 0
        for (i in jobs.indices) {
            val deadline = jobs[i].second
            for (j in deadline downTo 0) {
                if (!slot[j]) {
                    slot[j] = true
                    count++
                    profit += jobs[i].third
                    break
                }
            }
        }
        return intArrayOf(count, profit)
    }


    /**
     * Employee Free Time
     * We are given a list schedule of employees, which represents the working time for each employee.
     *
     * Each employee has a list of non-overlapping intervals.Intervals, and these intervals are in sorted order.
     *
     * Return the list of finite intervals representing common, positive-length free time for all employees, also in sorted order.
     *
     * Example 1:
     *
     * Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
     * Output: [[3,4]]
     * Explanation:
     * There are a total of three employees, and all common
     * free time intervals would be [-inf, 1], [3, 4], [10, inf].
     * We discard any intervals that contain inf as they aren't finite.
     *
     * 1---2
     *               5---6
     * 1------3
     *            4-----------------10
     *
     * Example 2:
     *
     * Input: schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
     * Output: [[5,6],[7,9]]
     *
     * 1---3
     *             6---7
     *   2---4
     *   2------5
     *                     9-----------------12
     *
     *
     *
     * (Even though we are representing intervals.Intervals in the form [x, y], the objects inside are intervals.Intervals, not lists or arrays.
     * For example, schedule[0][0].start = 1, schedule[0][0].end = 2, and schedule[0][0][0] is not defined.)
     *
     * Also, we wouldn't include intervals like [5, 5] in our answer, as they have zero length.
     *
     * Note:
     *
     * schedule and schedule[i] are lists with lengths in range [1, 50].
     * 0 <= schedule[i].start < schedule[i].end <= 10^8.
     * NOTE: input types have been changed on June 17, 2019. Please reset to default code definition to get new method signature
     */
    fun employeeFreeTime(schedule: List<List<IntArray>>): List<IntArray> {
        /*
        1. create a list of intervals
        2. and sort by start
        3. iterate and check for prev.end> curr.start, then merge the interval to prev.start
        4. else add the interval to result prev.end, curr.start
        5. update the prev = curr
         */
        val result = mutableListOf<IntArray>()

        //can use the list and sort it
        val list = mutableListOf<IntArray>()
        for (i in schedule) {
            list.addAll(i)
        }

        list.sortBy { it[0] }

        // or we can use the priority queue
        val pq = PriorityQueue<IntArray>() { a, b -> a[0] - b[0] }

        for (i in schedule) {
            pq.addAll(i)
        }

        var prev = pq.poll()

        while (pq.isNotEmpty()) {
            val curr = pq.poll()
            if (prev[1] < curr[0]) {
                result.add(intArrayOf(prev[1], curr[0]))
                prev = curr
            } else {
                prev[1] = maxOf(prev[1], curr[1])
            }
        }

//        var prev = list[0]
//
//        for(i in 1..list.lastIndex){
//            val curr = list[i]
//            if(prev[1] < curr[0]){
//                result.add(intArrayOf(prev[1], curr[0]))
//                prev = curr
//            }else{
//                prev[1] = maxOf(prev[1], curr[1])
//            }
//        }


        return result

    }

    /**
     * There is a car with capacity empty seats. The vehicle only drives east (i.e., it cannot turn around and drive west).
     *
     * You are given the integer capacity and an array trips where trips[i] = [numPassengersi, fromi, toi] indicates that the ith trip has
     * numPassengersi passengers and the locations to pick them up and drop them off are fromi and toi respectively.
     * The locations are given as the number of kilometers due east from the car's initial location.
     *
     * Return true if it is possible to pick up and drop off all passengers for all the given trips, or false otherwise.
     *
     *
     *
     * Example 1:
     *
     * Input: trips = [[2,1,5],[3,3,7]], capacity = 4
     * Output: false
     * Example 2:
     *
     * Input: trips = [[2,1,5],[3,3,7]], capacity = 5
     * Output: true
     */
    @Revise
    fun carPooling(trips: Array<IntArray>, capacity: Int): Boolean {
        /*
        1. create tree map with trips for and to with  pass & - passengers respectivelt
        2. iterate over map.values and add count and check if count > capacity return false
         */
        val map = TreeMap<Int, Int>()
        for (trip in trips) {
            map[trip[1]] = map.getOrDefault(trip[1], 0) + trip[0]
            map[trip[2]] = map.getOrDefault(trip[2], 0) - trip[0]
        }

        var count = 0
        for (value in map.values) {
            count += value
            if (count > capacity) return false
        }
        return true
    }

    /**
     *You are implementing a program to use as your calendar. We can add a new event if adding the event will not cause a double booking.
     *
     * A double booking happens when two events have some non-empty intersection (i.e., some moment is common to both events.).
     *
     * The event can be represented as a pair of integers start and end that represents a booking on the half-open interval [start, end),
     * the range of real numbers x such that start <= x < end.
     *
     * Implement the MyCalendar class:
     *
     * MyCalendar() Initializes the calendar object.
     * boolean book(int start, int end) Returns true if the event can be added to the calendar successfully
     * without causing a double booking. Otherwise, return false and do not add the event to the calendar.
     *
     *
     * Example 1:
     *
     * Input
     * ["MyCalendar", "book", "book", "book"]
     * [[], [10, 20], [15, 25], [20, 30]]
     * Output
     * [null, true, false, true]
     *
     * Explanation
     * MyCalendar = new MyCalendar();
     * myCalendar.book(10, 20); // return True
     * myCalendar.book(15, 25); // return False, It can not be booked because time 15 is already booked by another event.
     * myCalendar.book(20, 30); // return True, The event can be booked, as the first event takes every time less than 20, but not including 20.
     */
    /*
     TreeMap in Java (and similar data structures in other languages) internally uses a Red-Black tree to maintain its sort order.
     A Red-Black tree is a self-balancing binary search tree, which ensures that the tree remains balanced,
     thereby guaranteeing that operations like insert, delete, and search can be performed in (O(\log n)) time complexity, where (n) is the number of elements in the tree. The sort order of the TreeMap is determined by the natural ordering of its keys or by a Comparator provided at the time of the TreeMap creation.
     This allows the TreeMap to efficiently maintain its elements in a sorted order
     */
    inner class MyCalendar {
        private val list = mutableListOf<IntArray>()

        fun bookWithList(start: Int, end: Int): Boolean {
            /*
            1. iterate over the list and check for overlap
            2. if overlap return false
            3. else add to list and return true
            4. time complexity O(n)

             */
            for (i in list.indices) {
                if (list[i][0] < end && start < list[i][1]) return false
            }
            list.add(intArrayOf(start, end))
            return true
        }

        private val map = TreeMap<Int, Int>()

        @Important
        fun bookWithMap(start: Int, end: Int): Boolean {
            /*
            1. get the prev and next entry from map
            2. check for the overlap prev.end > start || end > next.start
            3. if overlap return false
            4. add to tree map and return true
            time complexity O(logn)
             */
            val prev = map.floorEntry(start)
            val next = map.ceilingEntry(start)

            if ((prev != null && prev.value > start) || (next != null && end > next.key))
                return false
            map[start] = end
            return true
        }
    }

    /**
     * You are implementing a program to use as your calendar. We can add a new event if adding the event will not cause a triple booking.
     *
     * A triple booking happens when three events have some non-empty intersection (i.e., some moment is common to all the three events.).
     *
     * The event can be represented as a pair of integers start and end that represents a booking on the half-open interval [start, end),
     * the range of real numbers x such that start <= x < end.
     *
     * Implement the MyCalendarTwo class:
     *
     * MyCalendarTwo() Initializes the calendar object.
     * boolean book(int start, int end) Returns true if the event can be added to the calendar successfully without causing a triple booking.
     * Otherwise, return false and do not add the event to the calendar.
     *
     *
     * Example 1:
     *
     * Input
     * ["MyCalendarTwo", "book", "book", "book", "book", "book", "book"]
     * [[], [10, 20], [50, 60], [10, 40], [5, 15], [5, 10], [25, 55]]
     * Output
     * [null, true, true, true, false, true, true]
     *
     * Explanation
     * MyCalendarTwo myCalendarTwo = new MyCalendarTwo();
     * myCalendarTwo.book(10, 20); // return True, The event can be booked.
     * myCalendarTwo.book(50, 60); // return True, The event can be booked.
     * myCalendarTwo.book(10, 40); // return True, The event can be double booked.
     * myCalendarTwo.book(5, 15);  // return False, The event cannot be booked, because it would result in a triple booking.
     * myCalendarTwo.book(5, 10); // return True, The event can be booked, as it does not use time 10 which is already double booked.
     * myCalendarTwo.book(25, 55); // return True, The event can be booked, as the time in [25, 40) will be double booked with the third event, the time [40, 50) will be single booked, and the time [50, 55) will be double booked with the second event.
     *
     */
    inner class MyCalender2 {

        private val map = TreeMap<Int, Int>()

        @Important
        fun book(start: Int, end: Int): Boolean {
            /*
            1. add the start and end to map with +1 and -1
            2. iterate over the map and check for count == 3
            3. if count == 3 then, dec the start and inc the end return false
            4. else return true
             */
            map[start] = map.getOrDefault(start, 0) + 1
            map[end] = map.getOrDefault(end, 0) - 1

            var count = 0
            for (entry in map) {
                count += entry.value
                if (count == 3) {
                    map[start] = map[start]!! - 1
                    map[end] = map[end]!! + 1
                    return false
                }
            }

            return true

        }
    }

    /**
     *
     * Topics
     * Companies
     * Hint
     * A k-booking happens when k events have some non-empty intersection (i.e., there is some time that is common to all k events.)
     *
     * You are given some events [startTime, endTime), after each given event,
     * return an integer k representing the maximum k-booking between all the previous events.
     *
     * Implement the MyCalendarThree class:
     *
     * MyCalendarThree() Initializes the object.
     * int book(int startTime, int endTime)
     * Returns an integer k representing the largest integer such that there exists a k-booking in the calendar.
     *
     *
     * Example 1:
     *
     * Input
     * ["MyCalendarThree", "book", "book", "book", "book", "book", "book"]
     * [[], [10, 20], [50, 60], [10, 40], [5, 15], [5, 10], [25, 55]]
     * Output
     * [null, 1, 1, 2, 3, 3, 3]
     *
     * 5----10
     *      10----20
     *
     *      10----
     *
     *
     * Explanation
     * MyCalendarThree myCalendarThree = new MyCalendarThree();
     * myCalendarThree.book(10, 20); // return 1
     * myCalendarThree.book(50, 60); // return 1
     * myCalendarThree.book(10, 40); // return 2
     * myCalendarThree.book(5, 15); // return 3
     * myCalendarThree.book(5, 10); // return 3
     * myCalendarThree.book(25, 55); // return 3
     */
    inner class MyCalender3 {

        private val timeline = TreeMap<Int, Int>()

        @Important
        @Revise
        fun maxKBookings(start: Int, end: Int): Int {
            timeline[start] = timeline.getOrDefault(start, 0) + 1
            // Decrement the counter for the end time
            timeline[end] = timeline.getOrDefault(end, 0) - 1

            var ongoing = 0 // Current number of ongoing events
            var maxBooking = 0 // Maximum number of concurrent events

            // Iterate through the timeline to find the maximum overlap
            for (count in timeline.values) {
                ongoing += count
                maxBooking = maxOf(maxBooking, ongoing)
            }

            return maxBooking
        }


    }


}