package main.java.GroupsHybrid.Data;

import java.util.ArrayList;
import java.util.Collections;

public class StudentScores {

	public final static int MAXIMUM_STUDENTS = 512;

	private static double[][] Distance = null;

	public StudentScores() {

		if (StudentScores.Distance == null) {
			StudentScores.Distance = new double[StudentScores.MAXIMUM_STUDENTS][StudentScores.MAXIMUM_STUDENTS];
			for (int i = 0; i < StudentScores.MAXIMUM_STUDENTS; i++) {
				for (int j = 0; j < StudentScores.MAXIMUM_STUDENTS; j++) {
					StudentScores.Distance[i][j] = -1;
				}
			}
		}
	}

	/**
	 * Given a studentId, sums up the score for that student
	 * 
	 * @param studentId
	 * @return
	 */
	protected int getSumScore(int studentId) {
		int scores[] = StudentScores.Scores[studentId - 1];
		int sum = 0;
		for (int s : scores) {
			sum += s;
		}
		return sum;
	}

	/**
	 * Returns the score of student with studentId
	 * 
	 * @param studentId
	 * @return
	 */
	protected int[] getScores(int studentId) {
		return StudentScores.Scores[studentId - 1];
	}

	/**
	 * Given four student IDs, return the GH value of the group
	 * 
	 * @param student1
	 * @param student2
	 * @param student3
	 * @param student4
	 * @return 
	 */
	public double getGhValue(int student1, int student2, int student3, int student4) {
		ArrayList<Integer> scores = new ArrayList<Integer>();
		scores.add(this.getSumScore(student1));
		scores.add(this.getSumScore(student2));
		scores.add(this.getSumScore(student3));
		scores.add(this.getSumScore(student4));
		Collections.sort(scores);

		double adValue = (scores.get(0) + scores.get(3)) / 2;
		double ghValue = (scores.get(3) - scores.get(0))
				/ (1 + Math.abs(adValue - scores.get(1)) + Math.abs(adValue - scores.get(2)));

		return ghValue;
	}

	/**
	 * Given four student IDs return the maximum Euclidean Distance of that group
	 * 
	 * @param student1
	 * @param student2
	 * @param student3
	 * @param student4
	 * @return 
	 */
	public double getMaxDistance(int student1, int student2, int student3, int student4) {
		ArrayList<Double> distances = new ArrayList<Double>();

		distances.add(this.getDistance(student1, student2));
		distances.add(this.getDistance(student1, student3));
		distances.add(this.getDistance(student1, student4));
		distances.add(this.getDistance(student2, student3));
		distances.add(this.getDistance(student2, student4));
		distances.add(this.getDistance(student3, student4));

		Collections.sort(distances);

		return distances.get(5);
	}

	/**
	 * Given two students, calculate the Euclidean Distance between them
	 * 
	 * @param student1
	 * @param student2
	 * @return 
	 */
	private double getDistance(int student1, int student2) {
		int s1 = student1 < student2 ? student1 : student2;
		int s2 = student1 < student2 ? student2 : student1;

		s1--;
		s2--;

		if (StudentScores.Distance[s1][s2] == -1) {
			int[] score1 = this.getScores(student1);
			int[] score2 = this.getScores(student2);

			double s = 0;
			for (int i = 0; i < score1.length; i++) {
				s += Math.pow(score1[i] - score2[i], 2);
			}
			StudentScores.Distance[s1][s2] = Math.sqrt(s);
		}

		return StudentScores.Distance[s1][s2];

	}
	
	/**
	 * 
	 * @return 
	 */
	public int[] getAllScores(){
		int [] allScores = new int[StudentScores.MAXIMUM_STUDENTS];
		for( int i = 0; i < StudentScores.MAXIMUM_STUDENTS; i++){
			allScores[i] = this.getSumScore(i+1);
		}
		return allScores;
	
	}
	
	private static int[][] Scores = new int[][] { { 2, 2, 2, 2, 2, 3, 3 }, { 2, 2, 1, 2, 2, 2, 2 },
			{ 2, 2, 2, 1, 1, 1, 2 }, { 1, 2, 2, 1, 2, 2, 3 }, { 2, 2, 2, 2, 2, 1, 1 }, { 3, 2, 2, 3, 1, 1, 1 },
			{ 2, 2, 2, 2, 2, 1, 1 }, { 1, 2, 2, 1, 1, 2, 1 }, { 2, 1, 2, 2, 1, 1, 1 }, { 2, 2, 2, 2, 3, 1, 2 },
			{ 2, 2, 2, 2, 2, 1, 1 }, { 3, 1, 2, 2, 2, 1, 1 }, { 2, 2, 2, 1, 2, 1, 1 }, { 1, 2, 2, 2, 2, 1, 3 },
			{ 1, 3, 2, 2, 2, 1, 2 }, { 2, 1, 2, 2, 1, 1, 3 }, { 2, 2, 2, 2, 2, 2, 2 }, { 2, 2, 2, 2, 2, 1, 1 },
			{ 1, 1, 2, 2, 2, 2, 1 }, { 3, 2, 2, 2, 1, 1, 1 }, { 2, 3, 2, 2, 3, 1, 2 }, { 1, 2, 1, 1, 1, 3, 3 },
			{ 2, 3, 2, 2, 3, 3, 3 }, { 2, 2, 2, 2, 2, 1, 1 }, { 2, 1, 3, 2, 2, 1, 1 }, { 1, 1, 1, 2, 2, 2, 1 },
			{ 2, 2, 2, 2, 1, 1, 1 }, { 3, 3, 2, 2, 2, 1, 1 }, { 2, 2, 2, 2, 2, 1, 1 }, { 2, 2, 2, 2, 2, 2, 2 },
			{ 2, 2, 2, 3, 3, 2, 1 }, { 2, 2, 2, 1, 2, 2, 1 }, { 2, 3, 2, 3, 2, 1, 1 }, { 3, 3, 2, 3, 2, 1, 2 },
			{ 1, 2, 1, 2, 2, 2, 3 }, { 2, 3, 2, 1, 2, 1, 1 }, { 2, 2, 2, 2, 2, 2, 1 }, { 2, 3, 2, 3, 2, 3, 3 },
			{ 2, 2, 2, 2, 2, 3, 1 }, { 2, 2, 2, 1, 1, 1, 1 }, { 2, 2, 3, 3, 2, 1, 2 }, { 3, 2, 2, 2, 2, 1, 1 },
			{ 2, 2, 2, 2, 2, 2, 1 }, { 2, 1, 3, 2, 2, 1, 1 }, { 2, 1, 2, 2, 3, 2, 1 }, { 1, 2, 2, 1, 2, 2, 1 },
			{ 2, 3, 3, 2, 3, 2, 1 }, { 3, 2, 2, 2, 3, 3, 3 }, { 1, 2, 2, 1, 2, 2, 2 }, { 3, 3, 2, 2, 3, 3, 2 },
			{ 2, 2, 2, 1, 2, 2, 1 }, { 2, 3, 2, 2, 2, 3, 2 }, { 2, 3, 3, 2, 2, 1, 1 }, { 3, 1, 2, 3, 3, 2, 1 },
			{ 2, 3, 2, 2, 3, 1, 1 }, { 1, 1, 2, 1, 2, 1, 1 }, { 2, 2, 2, 2, 2, 1, 1 }, { 1, 2, 2, 2, 1, 3, 2 },
			{ 3, 1, 2, 2, 2, 3, 1 }, { 1, 1, 1, 1, 2, 1, 1 }, { 2, 1, 1, 2, 2, 2, 2 }, { 2, 2, 2, 3, 2, 1, 1 },
			{ 2, 3, 3, 2, 2, 1, 1 }, { 2, 2, 2, 2, 1, 2, 3 }, { 2, 1, 2, 2, 2, 1, 1 }, { 3, 1, 3, 3, 3, 2, 1 },
			{ 2, 2, 1, 1, 2, 3, 1 }, { 2, 2, 2, 1, 2, 1, 2 }, { 2, 3, 1, 1, 2, 1, 1 }, { 1, 1, 2, 2, 2, 1, 1 },
			{ 2, 3, 2, 2, 2, 1, 1 }, { 2, 2, 3, 1, 3, 1, 1 }, { 2, 3, 2, 1, 2, 3, 3 }, { 3, 1, 2, 2, 2, 1, 2 },
			{ 2, 3, 2, 2, 3, 2, 3 }, { 1, 2, 2, 2, 2, 1, 1 }, { 2, 3, 3, 2, 3, 3, 3 }, { 1, 2, 2, 2, 2, 3, 2 },
			{ 2, 2, 1, 2, 1, 1, 1 }, { 1, 3, 2, 2, 1, 1, 3 }, { 2, 1, 2, 2, 2, 1, 1 }, { 2, 3, 3, 3, 2, 3, 3 },
			{ 1, 2, 2, 2, 3, 3, 2 }, { 2, 2, 1, 1, 2, 1, 1 }, { 2, 2, 2, 2, 2, 2, 1 }, { 1, 3, 3, 2, 1, 2, 2 },
			{ 2, 2, 2, 2, 2, 1, 1 }, { 3, 1, 3, 3, 3, 3, 2 }, { 2, 2, 2, 2, 2, 1, 1 }, { 2, 3, 2, 2, 2, 2, 2 },
			{ 3, 2, 3, 3, 2, 3, 2 }, { 1, 2, 3, 2, 1, 2, 2 }, { 3, 3, 2, 2, 2, 1, 1 }, { 2, 2, 2, 2, 3, 1, 1 },
			{ 2, 2, 1, 2, 2, 2, 1 }, { 2, 2, 2, 1, 2, 1, 1 }, { 2, 2, 2, 2, 2, 1, 1 }, { 2, 2, 1, 2, 2, 1, 1 },
			{ 2, 1, 1, 3, 3, 2, 2 }, { 2, 1, 1, 2, 2, 3, 1 }, { 2, 2, 3, 3, 2, 1, 1 }, { 2, 2, 3, 2, 1, 1, 1 },
			{ 2, 2, 2, 2, 1, 1, 1 }, { 2, 2, 2, 2, 3, 2, 3 }, { 2, 3, 2, 2, 2, 1, 1 }, { 2, 3, 2, 2, 2, 2, 2 },
			{ 2, 2, 2, 2, 2, 2, 2 }, { 2, 3, 2, 2, 2, 1, 3 }, { 3, 2, 3, 2, 3, 3, 2 }, { 2, 3, 2, 2, 3, 2, 2 },
			{ 2, 3, 2, 2, 2, 2, 3 }, { 3, 3, 2, 2, 2, 1, 2 }, { 2, 1, 2, 1, 1, 1, 1 }, { 1, 1, 1, 2, 2, 2, 1 },
			{ 2, 2, 2, 3, 2, 2, 1 }, { 2, 2, 2, 2, 2, 1, 1 }, { 2, 2, 2, 2, 2, 2, 2 }, { 1, 2, 2, 1, 1, 2, 1 },
			{ 2, 3, 3, 3, 2, 2, 3 }, { 2, 3, 1, 2, 2, 1, 1 }, { 2, 2, 2, 2, 1, 1, 1 }, { 2, 2, 2, 1, 1, 2, 2 },
			{ 2, 3, 2, 2, 2, 2, 2 }, { 3, 1, 2, 2, 2, 3, 2 }, { 2, 2, 2, 2, 2, 3, 3 }, { 2, 1, 2, 2, 2, 2, 2 },
			{ 2, 3, 3, 2, 2, 3, 3 }, { 2, 3, 2, 2, 2, 1, 3 }, { 2, 3, 2, 2, 1, 2, 3 }, { 2, 2, 1, 1, 1, 1, 1 },
			{ 2, 2, 2, 2, 2, 2, 2 }, { 2, 3, 2, 3, 2, 3, 3 }, { 3, 2, 2, 2, 1, 1, 1 }, { 2, 1, 2, 2, 1, 1, 1 },
			{ 2, 2, 2, 2, 2, 2, 1 }, { 2, 1, 3, 2, 2, 1, 1 }, { 2, 2, 3, 3, 3, 2, 2 }, { 2, 2, 3, 3, 2, 1, 1 },
			{ 2, 2, 2, 2, 1, 1, 2 }, { 2, 2, 2, 2, 3, 3, 2 }, { 2, 2, 2, 3, 2, 1, 1 }, { 2, 2, 1, 2, 2, 2, 2 },
			{ 2, 3, 2, 3, 3, 1, 3 }, { 2, 1, 2, 2, 2, 1, 2 }, { 2, 1, 2, 2, 2, 2, 2 }, { 2, 2, 2, 3, 3, 2, 1 },
			{ 2, 2, 2, 2, 2, 2, 1 }, { 2, 2, 2, 2, 3, 2, 1 }, { 2, 3, 2, 3, 3, 3, 2 }, { 1, 3, 3, 2, 2, 3, 2 },
			{ 2, 2, 1, 2, 2, 1, 1 }, { 2, 1, 2, 2, 1, 1, 1 }, { 2, 2, 2, 2, 1, 2, 1 }, { 2, 1, 2, 3, 3, 1, 3 },
			{ 1, 2, 3, 3, 2, 2, 2 }, { 2, 3, 2, 2, 3, 2, 2 }, { 2, 1, 2, 3, 3, 3, 1 }, { 2, 1, 2, 2, 2, 1, 1 },
			{ 3, 3, 3, 3, 3, 2, 3 }, { 2, 2, 2, 2, 1, 1, 1 }, { 2, 2, 3, 2, 2, 1, 1 }, { 2, 2, 2, 1, 2, 3, 3 },
			{ 3, 2, 3, 2, 2, 2, 1 }, { 2, 2, 3, 2, 2, 1, 1 }, { 2, 2, 2, 1, 1, 3, 2 }, { 2, 2, 3, 2, 2, 1, 1 },
			{ 3, 1, 2, 2, 3, 1, 1 }, { 1, 2, 1, 1, 2, 1, 1 }, { 1, 2, 1, 1, 1, 1, 2 }, { 2, 2, 2, 1, 2, 1, 2 },
			{ 2, 2, 1, 2, 2, 1, 1 }, { 2, 1, 2, 2, 2, 1, 1 }, { 2, 3, 2, 2, 2, 1, 1 }, { 2, 3, 2, 3, 2, 1, 1 },
			{ 2, 1, 2, 2, 2, 1, 1 }, { 1, 1, 2, 2, 1, 1, 1 }, { 2, 2, 2, 2, 3, 3, 1 }, { 2, 2, 2, 2, 2, 1, 1 },
			{ 2, 2, 2, 1, 2, 1, 1 }, { 2, 1, 1, 2, 3, 2, 2 }, { 2, 2, 2, 1, 1, 1, 2 }, { 2, 1, 2, 1, 2, 1, 1 },
			{ 1, 2, 2, 2, 2, 3, 3 }, { 2, 2, 2, 2, 1, 1, 1 }, { 2, 2, 2, 2, 3, 2, 1 }, { 2, 2, 1, 2, 2, 1, 2 },
			{ 2, 2, 2, 3, 2, 1, 1 }, { 1, 2, 1, 3, 2, 3, 3 }, { 3, 2, 2, 2, 2, 1, 1 }, { 1, 3, 3, 2, 2, 3, 3 },
			{ 2, 2, 2, 2, 2, 1, 2 }, { 2, 3, 3, 2, 2, 3, 3 }, { 2, 2, 2, 2, 2, 2, 2 }, { 2, 3, 2, 3, 2, 1, 1 },
			{ 1, 1, 1, 2, 2, 1, 2 }, { 3, 3, 3, 2, 2, 1, 2 }, { 1, 3, 3, 2, 1, 1, 2 }, { 2, 2, 1, 2, 1, 3, 3 },
			{ 2, 2, 2, 3, 2, 1, 1 }, { 2, 3, 2, 2, 3, 1, 1 }, { 2, 1, 2, 2, 2, 1, 1 }, { 2, 3, 1, 2, 2, 2, 2 },
			{ 2, 2, 2, 2, 2, 2, 1 }, { 2, 2, 1, 2, 2, 2, 1 }, { 3, 2, 3, 3, 2, 1, 1 }, { 2, 2, 2, 2, 2, 1, 1 },
			{ 1, 1, 2, 2, 3, 3, 1 }, { 2, 2, 3, 3, 3, 2, 1 }, { 2, 3, 1, 2, 2, 1, 2 }, { 2, 2, 2, 2, 2, 1, 1 },
			{ 3, 3, 2, 3, 3, 1, 1 }, { 2, 2, 2, 2, 3, 2, 2 }, { 3, 3, 3, 1, 2, 1, 2 }, { 2, 1, 3, 1, 2, 1, 1 },
			{ 2, 2, 2, 1, 1, 1, 1 }, { 2, 2, 2, 2, 2, 3, 2 }, { 2, 2, 3, 2, 2, 2, 1 }, { 2, 2, 2, 2, 2, 2, 2 },
			{ 2, 2, 2, 3, 2, 3, 1 }, { 2, 1, 2, 2, 2, 2, 2 }, { 3, 2, 3, 2, 2, 1, 1 }, { 1, 1, 2, 2, 2, 2, 1 },
			{ 2, 2, 1, 1, 2, 1, 1 }, { 3, 2, 2, 2, 2, 1, 1 }, { 2, 2, 3, 3, 3, 3, 2 }, { 2, 1, 1, 1, 2, 1, 3 },
			{ 1, 2, 1, 2, 2, 1, 1 }, { 2, 2, 2, 2, 1, 1, 1 }, { 1, 2, 2, 1, 1, 1, 1 }, { 1, 2, 1, 2, 2, 2, 3 },
			{ 2, 2, 2, 2, 2, 1, 1 }, { 2, 3, 2, 2, 3, 1, 1 }, { 3, 2, 2, 2, 2, 2, 3 }, { 3, 3, 2, 2, 2, 2, 1 },
			{ 2, 2, 2, 2, 1, 1, 1 }, { 2, 2, 2, 2, 3, 2, 2 }, { 2, 1, 3, 2, 2, 2, 1 }, { 2, 2, 1, 2, 2, 3, 3 },
			{ 1, 3, 2, 2, 2, 2, 2 }, { 2, 2, 2, 1, 2, 3, 3 }, { 2, 3, 2, 2, 2, 3, 3 }, { 2, 2, 2, 3, 3, 1, 2 },
			{ 1, 3, 3, 2, 2, 3, 3 }, { 3, 3, 3, 1, 2, 2, 1 }, { 2, 2, 2, 2, 2, 1, 2 }, { 2, 3, 2, 3, 2, 2, 3 },
			{ 2, 2, 2, 2, 2, 1, 1 }, { 2, 2, 2, 2, 2, 1, 1 }, { 1, 2, 1, 2, 2, 2, 1 }, { 2, 3, 2, 3, 3, 1, 3 },
			{ 2, 2, 1, 2, 2, 1, 2 }, { 2, 2, 2, 2, 1, 2, 2 }, { 2, 2, 3, 2, 2, 2, 3 }, { 2, 2, 2, 3, 1, 1, 1 },
			{ 2, 2, 2, 2, 2, 1, 2 }, { 2, 2, 2, 2, 2, 2, 2 }, { 2, 2, 1, 2, 2, 2, 2 }, { 3, 2, 3, 3, 2, 1, 1 },
			{ 2, 1, 1, 1, 1, 1, 1 }, { 3, 1, 1, 1, 1, 1, 1 }, { 3, 2, 2, 2, 2, 2, 2 }, { 1, 1, 1, 2, 2, 1, 2 },
			{ 2, 2, 3, 2, 2, 3, 3 }, { 2, 2, 2, 1, 2, 1, 1 }, { 3, 3, 2, 2, 2, 1, 2 }, { 2, 2, 1, 1, 2, 1, 1 },
			{ 1, 2, 2, 3, 2, 3, 2 }, { 3, 2, 2, 2, 2, 2, 3 }, { 1, 2, 2, 2, 2, 2, 1 }, { 2, 2, 2, 3, 2, 2, 1 },
			{ 2, 1, 2, 2, 2, 2, 2 }, { 2, 2, 2, 2, 2, 1, 1 }, { 2, 2, 2, 2, 2, 2, 2 }, { 2, 3, 2, 2, 2, 1, 2 },
			{ 2, 3, 2, 2, 2, 1, 1 }, { 3, 2, 2, 2, 2, 1, 3 }, { 2, 1, 2, 2, 2, 1, 1 }, { 3, 3, 3, 2, 1, 3, 1 },
			{ 1, 1, 1, 2, 3, 3, 2 }, { 2, 1, 1, 2, 2, 3, 1 }, { 1, 3, 2, 2, 1, 3, 3 }, { 2, 2, 2, 2, 2, 3, 2 },
			{ 2, 2, 1, 2, 1, 3, 2 }, { 2, 2, 1, 2, 2, 1, 1 }, { 2, 1, 2, 1, 1, 1, 1 }, { 2, 2, 2, 1, 2, 1, 1 },
			{ 2, 1, 3, 1, 2, 1, 1 }, { 2, 2, 2, 1, 1, 3, 1 }, { 2, 1, 1, 2, 2, 2, 2 }, { 3, 1, 3, 2, 2, 1, 1 },
			{ 3, 2, 2, 2, 2, 2, 1 }, { 2, 2, 3, 3, 2, 1, 2 }, { 2, 2, 2, 2, 2, 1, 1 }, { 2, 2, 2, 2, 2, 3, 2 },
			{ 3, 2, 3, 2, 1, 2, 1 }, { 3, 2, 2, 2, 2, 1, 1 }, { 2, 3, 2, 3, 2, 1, 2 }, { 2, 2, 1, 1, 2, 3, 2 },
			{ 1, 3, 1, 1, 2, 1, 2 }, { 2, 2, 2, 3, 2, 3, 1 }, { 2, 3, 3, 2, 2, 1, 1 }, { 2, 3, 3, 3, 2, 3, 2 },
			{ 3, 2, 2, 2, 2, 2, 1 }, { 2, 2, 2, 2, 2, 1, 2 }, { 2, 3, 3, 2, 2, 2, 2 }, { 2, 2, 3, 2, 2, 2, 1 },
			{ 2, 2, 2, 1, 1, 2, 2 }, { 2, 2, 2, 2, 2, 2, 2 }, { 1, 2, 2, 3, 2, 2, 1 }, { 2, 2, 2, 2, 2, 1, 2 },
			{ 1, 2, 2, 3, 2, 1, 1 }, { 2, 3, 3, 3, 2, 2, 1 }, { 2, 3, 2, 2, 1, 3, 3 }, { 1, 1, 3, 1, 1, 2, 2 },
			{ 3, 2, 2, 3, 2, 3, 1 }, { 2, 2, 3, 2, 2, 2, 1 }, { 1, 1, 1, 2, 2, 1, 2 }, { 3, 1, 2, 2, 2, 2, 1 },
			{ 2, 2, 3, 3, 3, 2, 3 }, { 2, 2, 2, 2, 1, 1, 1 }, { 1, 2, 2, 2, 2, 1, 1 }, { 3, 2, 2, 1, 2, 2, 1 },
			{ 3, 2, 3, 2, 1, 1, 1 }, { 1, 1, 1, 1, 3, 2, 1 }, { 2, 2, 3, 2, 1, 2, 2 }, { 2, 1, 2, 2, 2, 1, 1 },
			{ 2, 3, 2, 1, 2, 3, 3 }, { 2, 2, 2, 3, 2, 1, 1 }, { 2, 2, 2, 2, 3, 3, 2 }, { 2, 1, 2, 2, 2, 1, 1 },
			{ 2, 3, 2, 1, 2, 1, 2 }, { 1, 3, 3, 1, 2, 1, 3 }, { 2, 2, 2, 3, 3, 2, 1 }, { 3, 2, 2, 2, 1, 2, 1 },
			{ 2, 2, 3, 2, 2, 1, 1 }, { 2, 2, 2, 2, 2, 1, 1 }, { 2, 2, 1, 2, 2, 2, 2 }, { 2, 2, 2, 2, 2, 2, 3 },
			{ 2, 2, 2, 2, 2, 1, 3 }, { 2, 2, 2, 2, 2, 1, 1 }, { 2, 3, 2, 2, 2, 3, 3 }, { 2, 3, 2, 2, 2, 3, 3 },
			{ 3, 2, 2, 2, 2, 1, 1 }, { 2, 2, 2, 1, 2, 1, 1 }, { 2, 2, 1, 2, 2, 1, 1 }, { 2, 2, 2, 2, 2, 2, 3 },
			{ 2, 2, 2, 2, 2, 2, 1 }, { 2, 2, 1, 3, 2, 1, 1 }, { 2, 2, 2, 3, 3, 3, 2 }, { 2, 2, 2, 3, 2, 1, 1 },
			{ 1, 1, 1, 2, 2, 2, 1 }, { 1, 2, 3, 3, 2, 2, 2 }, { 1, 2, 2, 2, 2, 1, 1 }, { 2, 3, 2, 3, 2, 1, 1 },
			{ 2, 1, 2, 2, 2, 2, 1 }, { 2, 2, 1, 2, 3, 3, 3 }, { 2, 2, 2, 1, 2, 1, 2 }, { 3, 2, 3, 2, 2, 1, 1 },
			{ 3, 2, 2, 2, 2, 1, 1 }, { 2, 2, 2, 2, 2, 3, 3 }, { 2, 1, 2, 2, 2, 3, 2 }, { 2, 1, 2, 2, 3, 2, 1 },
			{ 2, 2, 1, 2, 1, 1, 1 }, { 3, 3, 3, 2, 3, 2, 3 }, { 3, 3, 3, 2, 2, 1, 2 }, { 1, 3, 3, 2, 1, 1, 1 },
			{ 2, 2, 2, 2, 3, 1, 2 }, { 2, 2, 2, 2, 2, 1, 1 }, { 2, 2, 3, 2, 2, 3, 3 }, { 2, 1, 1, 1, 2, 1, 1 },
			{ 2, 1, 1, 1, 2, 1, 1 }, { 3, 2, 2, 2, 2, 1, 1 }, { 2, 2, 2, 2, 1, 1, 1 }, { 3, 1, 2, 2, 2, 2, 1 },
			{ 3, 2, 1, 2, 2, 2, 2 }, { 1, 1, 1, 2, 2, 2, 1 }, { 1, 2, 1, 1, 2, 2, 1 }, { 2, 2, 2, 1, 2, 2, 1 },
			{ 3, 2, 2, 3, 2, 2, 2 }, { 2, 2, 1, 2, 3, 2, 3 }, { 2, 3, 2, 2, 1, 2, 3 }, { 2, 1, 1, 1, 2, 3, 2 },
			{ 2, 2, 1, 2, 2, 2, 2 }, { 2, 2, 3, 2, 3, 2, 1 }, { 2, 2, 2, 2, 2, 2, 1 }, { 3, 2, 2, 2, 2, 1, 1 },
			{ 2, 2, 2, 1, 2, 3, 1 }, { 2, 1, 2, 2, 2, 1, 1 }, { 1, 2, 2, 2, 2, 1, 2 }, { 2, 2, 2, 1, 2, 1, 1 },
			{ 1, 2, 2, 1, 2, 2, 2 }, { 1, 1, 2, 1, 1, 1, 1 }, { 3, 1, 2, 3, 2, 2, 2 }, { 2, 3, 2, 2, 2, 1, 1 },
			{ 1, 2, 3, 3, 3, 3, 1 }, { 2, 1, 2, 1, 1, 1, 1 }, { 2, 2, 1, 3, 2, 3, 3 }, { 2, 3, 2, 2, 2, 2, 2 },
			{ 2, 3, 1, 1, 3, 3, 3 }, { 2, 2, 2, 1, 2, 1, 1 }, { 2, 3, 2, 2, 3, 2, 3 }, { 2, 2, 1, 2, 3, 1, 1 },
			{ 2, 2, 2, 3, 3, 1, 1 }, { 3, 2, 1, 1, 2, 1, 2 }, { 3, 2, 2, 2, 2, 2, 2 }, { 1, 2, 1, 1, 1, 1, 1 },
			{ 3, 2, 2, 2, 2, 1, 1 }, { 1, 2, 2, 2, 2, 2, 1 }, { 2, 2, 2, 3, 2, 2, 1 }, { 1, 1, 1, 3, 3, 2, 1 },
			{ 1, 1, 1, 1, 2, 1, 1 }, { 2, 3, 3, 2, 3, 2, 3 }, { 2, 2, 2, 2, 2, 2, 1 }, { 2, 2, 2, 2, 2, 1, 1 },
			{ 2, 1, 2, 2, 2, 1, 1 }, { 2, 2, 2, 2, 2, 1, 2 }, { 3, 2, 3, 1, 1, 1, 2 }, { 2, 2, 2, 2, 2, 2, 1 },
			{ 3, 2, 2, 2, 3, 1, 1 }, { 2, 3, 3, 3, 2, 3, 3 }, { 2, 2, 2, 2, 1, 2, 1 }, { 2, 3, 2, 2, 2, 1, 3 },
			{ 3, 2, 2, 1, 1, 1, 1 }, { 2, 2, 2, 2, 1, 2, 1 }, { 2, 2, 2, 2, 2, 1, 2 }, { 2, 2, 2, 2, 1, 1, 1 },
			{ 1, 1, 3, 2, 2, 1, 1 }, { 2, 3, 3, 2, 2, 2, 2 }, { 2, 2, 2, 1, 2, 2, 1 }, { 2, 2, 2, 2, 1, 1, 1 },
			{ 2, 2, 2, 1, 2, 1, 1 }, { 2, 2, 2, 3, 2, 2, 1 }, { 2, 3, 2, 2, 2, 1, 1 }, { 2, 1, 1, 1, 2, 2, 2 },
			{ 2, 3, 2, 2, 2, 2, 3 }, { 2, 2, 2, 1, 1, 1, 1 }, { 2, 3, 2, 2, 2, 3, 3 }, { 2, 2, 2, 1, 1, 1, 1 },
			{ 3, 2, 3, 2, 2, 1, 1 }, { 2, 2, 2, 2, 2, 1, 1 }, { 2, 3, 3, 3, 3, 3, 3 }, { 2, 2, 2, 2, 2, 1, 1 },
			{ 3, 2, 3, 3, 2, 3, 1 }, { 2, 2, 2, 2, 2, 1, 1 }, { 2, 2, 1, 2, 1, 3, 2 }, { 2, 2, 1, 1, 2, 3, 3 },
			{ 2, 2, 1, 2, 2, 2, 2 }, { 2, 2, 2, 2, 2, 1, 1 }, { 2, 2, 1, 3, 3, 2, 1 }, { 2, 2, 2, 2, 2, 3, 1 },
			{ 2, 2, 2, 2, 2, 1, 1 }, { 2, 2, 2, 2, 2, 1, 1 }, { 2, 1, 2, 2, 2, 1, 1 }, { 2, 2, 2, 2, 2, 1, 1 },
			{ 2, 2, 2, 1, 1, 1, 1 }, { 2, 2, 1, 2, 2, 1, 1 }, { 1, 2, 3, 2, 2, 2, 1 }, { 1, 2, 1, 2, 1, 1, 3 },
			{ 3, 1, 2, 2, 1, 1, 1 }, { 1, 2, 3, 2, 1, 3, 3 }, { 2, 2, 1, 2, 2, 2, 2 }, { 2, 2, 2, 2, 2, 1, 1 },
			{ 2, 2, 3, 3, 2, 1, 1 }, { 2, 3, 3, 2, 2, 3, 3 }, { 2, 2, 2, 2, 2, 2, 1 }, { 2, 1, 2, 2, 1, 1, 1 },
			{ 2, 3, 2, 2, 1, 1, 1 }, { 2, 2, 2, 2, 3, 1, 1 }, { 2, 1, 2, 2, 1, 1, 1 }, { 2, 2, 2, 3, 2, 2, 2 },
			{ 2, 2, 2, 2, 2, 2, 1 }, { 2, 2, 2, 3, 2, 1, 1 }, { 1, 1, 1, 2, 2, 1, 1 }, { 2, 2, 2, 2, 2, 1, 2 },
			{ 2, 2, 2, 3, 3, 3, 1 }, { 2, 3, 3, 2, 2, 2, 3 }, { 2, 2, 2, 2, 2, 2, 2 }, { 2, 3, 2, 1, 2, 2, 2 },
			{ 2, 3, 2, 2, 2, 1, 1 }, { 1, 2, 2, 1, 2, 1, 2 }, { 3, 2, 2, 2, 2, 1, 1 }, { 2, 1, 2, 2, 2, 1, 1 },
			{ 2, 2, 2, 2, 2, 2, 2 }, { 2, 1, 2, 1, 2, 1, 2 }, { 2, 2, 3, 2, 2, 2, 3 }, { 2, 2, 2, 2, 1, 1, 1 },
			{ 3, 1, 2, 2, 2, 1, 1 }, { 2, 2, 2, 2, 2, 1, 1 }, { 2, 1, 3, 2, 1, 1, 1 }, { 3, 1, 3, 3, 2, 1, 1 },
			{ 1, 1, 2, 1, 1, 2, 2 }, { 3, 3, 2, 2, 3, 1, 1 }, { 2, 3, 1, 2, 2, 2, 2 }, { 2, 1, 2, 3, 2, 1, 1 },
			{ 2, 2, 1, 2, 2, 2, 2 }, { 2, 3, 2, 3, 2, 3, 3 }, { 2, 2, 2, 2, 2, 2, 2 }, { 2, 2, 3, 2, 2, 1, 1 },
			{ 2, 2, 1, 2, 2, 1, 1 }, { 2, 1, 2, 2, 2, 2, 1 }, { 3, 2, 1, 2, 1, 1, 1 }, { 2, 3, 3, 2, 2, 1, 2 },
			{ 1, 2, 2, 2, 2, 1, 2 }, { 3, 3, 3, 2, 2, 1, 2 }, { 3, 3, 3, 3, 2, 1, 1 }, { 2, 1, 1, 2, 2, 3, 1 },
			{ 2, 2, 3, 1, 2, 3, 2 }, { 2, 1, 1, 2, 3, 1, 2 }, { 2, 2, 2, 2, 2, 1, 1 }, { 2, 2, 3, 2, 3, 1, 2 },
			{ 2, 2, 2, 1, 2, 3, 1 }, { 1, 2, 1, 2, 3, 2, 3 } };
}
