class Peashooter extends Plants {
  
    float ATTACK_INTERVAL_SECONDS = 2f;
    float DAMAGE_AMOUNT = 10;
    
    float attackIntervalCounter = 0f;
    
    public Peashooter(int row, int column) {
      super(row, column);
      
      health = 50;
      
      sprite.spritesheet = warriorImage;
      sprite.spriteFootOffset = 62;
      sprite.animations.put("idle", new Animation(0, 0, 128, 128, 35, 1f/20f, true));
      sprite.animations.put("attack", new Animation(0, 128*2, 128, 128, 27, 1f/20f, false));
      sprite.animations.put("die", new Animation(0, 128*3, 128, 128, 35, 1f/20f, false));
      sprite.changeAnimation("idle");
    }
    

    @Override
    public void update(float secondsElapsed) {
      super.update(secondsElapsed);
      if (health <= 0) {
        health = 0;
        if (!sprite.currentAnimationName.equals("die")) {
          sprite.changeAnimation("die");
        }
        else if (sprite.isAnimationComplete()) {
          dead = true;
        }
        return;
      }
      


      Zombie closestZombie = null;
      float closestDistance = 0;
      for (Zombie e : zombie.get(this.row)) {
        float distanceToZombie = e.xPos - this.column;
        if (e.health > 0 && distanceToZombie >= 0 && distanceToZombie <= 0.1f) {
          if (closestZombie == null || closestDistance > distanceToZombie) {
            closestZombie = e;
            closestDistance = distanceToZombie;
          }
        }
      }
      


      attackIntervalCounter += secondsElapsed;
      if (attackIntervalCounter >= ATTACK_INTERVAL_SECONDS) {
        attackIntervalCounter -= ATTACK_INTERVAL_SECONDS;
        if (closestEnemy != null) {
          sprite.changeAnimation("attack");
        }
      }
      

      if (sprite.currentAnimationName.equals("attack") && sprite.isAnimationComplete()) {
        sprite.changeAnimation("idle");
        if (closestZombie != null) {
          closestZombie.health -= DAMAGE_AMOUNT;
        }
      }
    }
  }
  