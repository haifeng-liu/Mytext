
1、添加application并集成baseapplication:
2、侧滑菜单：
         @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction()==MotionEvent.ACTION_DOWN){
            x1=event.getX();
            y1=event.getY();
            flg=true;
        }else if (event.getAction()==MotionEvent.ACTION_UP){
            x2=event.getX();
            y2=event.getY();
            if (null!=mSlideViewLeft) {
                if (flg&&x1<15&&x2 - x1 > 50) {
                    if (!mSlideViewLeft.isShow()) {
                        mSlideViewLeft.show();
                        flg=false;
                    }
                } else if (x1 - x2 > 50) {
                    if (mSlideViewLeft.isShow()) {
                        mSlideViewLeft.dismiss();
                        flg=false;
                    }
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }
    
