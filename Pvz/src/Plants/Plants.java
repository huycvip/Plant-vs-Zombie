abstract class Plants {
    Sprite sprite;
    int health;
    boolean dead;
    int row, column;
    
    public Plants(int row, int column) {
      this.row = row;
      this.column = column;
      
      sprite = new Sprite();   
      sprite.position = World.getWorldPos(row, column);
    }
    
   
    
    void draw() {
      sprite.draw();
      
      textSize(12);
      text(Integer.toString(health), sprite.position.x , sprite.position.y );
    }
  }
  