public class Candy {
    /*
        two pass solution
            1. start from 0 to the end, distribute candies (in ascending rating)
            2. go opposite direction from end, distribute candies (in inversely ascending rating)
    */
    public int candy(int[] ratings) {
        int[] candies = new int[ratings.length];
        int i = 0;
        // first pass: ascending order
        while (i < candies.length - 1) {
            while (i < candies.length - 1 && ratings[i] >= ratings[i + 1]) i++;
            while (i < candies.length - 1 && ratings[i] < ratings[i + 1]) {
                candies[i + 1] = candies[i] + 1;
                i++;
            }
        }
        System.out.println(i);

        // second pass descending order
        while (i > 0) {
            while (i > 0 && ratings[i] >= ratings[i - 1]) i--;
            while (i > 0 && ratings[i] < ratings[i - 1]) {
                // we only give more candy to children who have
                // not got what they deserve!
                // for whom already get more candies, we will do nothing
                // otherwise they will lose candies
                if (candies[i - 1] <= candies[i])
                    candies[i - 1] = candies[i] + 1;
                i--;
            }
        }

        int count = 0;
        for (i = 0; i < candies.length; i++) {
            count += candies[i];
        }

        return count + candies.length;
    }
}
