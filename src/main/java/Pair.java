public class Pair {
  private String task;
  private boolean isDone;

  public Pair(String task) {
      this.task = task;
      this.isDone = false;
  }

  public void setStatus(boolean isDone) {
      this.isDone = isDone;
  }

  public String toString() {
      return "[" + (isDone ? "X" : " ") + "] " + task;
  }
}