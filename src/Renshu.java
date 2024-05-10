class Renshu {
    // xを2倍にして返す関数
    public int doubleValue(int x) {
        return x * 2;
    }

    //ここに続きを実装していく。
    public int sumUpToN(int x) {
        int sum = 0;
        for(int i = 1; i <= x; i ++) {
            sum += i;
        }
        return sum;
    }

    public int sumFromPtoQ(int x, int y) {
        if(x > y) {
            return -1;
        }
        else {
            int sum = 0;
            for(int i = x; i <= y; i ++) {
                sum += i;
            }
            return sum;
        }
    }

    public int sumFromArrayIndex(int[] a, int index) {
        if(index >= a.length) {
            return -1;
        }
        else {
            int sum = 0;
            while (index < a.length) {
                sum += a[index];
                index ++;
            }
            return sum;
        }
    }

    public int selectMaxValue(int[] a) {
        int max = a[0];
        for(int i = 1; i < a.length; i ++) {
            if(a[i] > max) {
                max = a[i];
            }
        }
        return max;
    }

    public int selectMinValue(int[] a) {
        int min = a[0];
        for(int i = 1; i < a.length; i ++) {
            if(min > a[i]) {
                min = a[i];
            }
        }
        return min;
    }

    public int selectMaxIndex(int[] a) {
        int max = a[0];
        int index = 0;
        for(int i = 1; i < a.length; i ++) {
            if(a[i] > max) {
                max = a[i];
                index = i;
            }
        }
        return index;
    }

    public int selectMinIndex(int[] a) {
        int min = a[0];
        int index = 0;
        for(int i = 1; i < a.length; i ++) {
            if(min > a[i]) {
                min = a[i];
                index = i;
            }
        }
        return index;
    }

    public void swapArrayElements(int[] a, int indexA, int indexB) {
        final int temp = a[indexA];
        a[indexA] = a[indexB];
        a[indexB] = temp;
    }

    public boolean swapTwoArrays(int[] a, int[] b) {
        if(a.length != b.length) {
            return false;
        }
        else {
            for(int i = 0; i < a.length; i ++) {
                final int temp = a[i];
                a[i] = b[i];
                b[i] = temp;
            }
            return true;
        }
    }

    public void bubbleSort(int[] a) {
        for(int i = 0; i < a.length; i ++) {
            for(int j = i+1; j < a.length; j ++) {
                if(a[i] > a[j]) {
                    final int temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }
    }

    public void quickSort(int[] a) {
        quickSort(a, 0, a.length);
    }
    private static void quickSort(int[] a, int left, int right) {
        if (left >= right - 1) {
            return;
        }
        int pivot = a[right - 1];
        int pivotIndex = left;
        for (int i = left; i < right - 1; i++) {
            if (a[i] < pivot) {
                int temp = a[pivotIndex];
                a[pivotIndex] = a[i];
                a[i] = temp;
                pivotIndex++;
            }
        }
        int temp = a[pivotIndex];
        a[pivotIndex] = a[right - 1];
        a[right - 1] = temp;

        quickSort(a, left, pivotIndex);
        quickSort(a, pivotIndex + 1, right);
    }
}
