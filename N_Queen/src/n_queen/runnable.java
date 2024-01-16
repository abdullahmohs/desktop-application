
package n_queen;

public class runnable implements Runnable {
    int row;
    int size;
    runnable(int size ,int row){
    this.row=row;
    this.size=size;
    }
    @Override
    public void run(){
        solving s = new solving();
        s.solveNQ(size,row);
    }
}
