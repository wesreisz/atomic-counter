package com.wesleyreisz.exploring;

/**
 * working
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Starting" );
        App app = new App();
        app.run();
        System.out.println( "Endin" );
    }

    public void run(){
        AtomicCounter ac = new AtomicCounter(10);
        int c = 1892300;
        while (c>0){
            ac.add();
            c-=1;
        }

        ac.print();

    }

    public class AtomicCounter{
        byte[] counter;
        public AtomicCounter(int size){
            counter = new byte[size];
        }
        public void add(){
            add(0,CarryOverEnum.NoCarryOverValue);
        }

        private void add(int position, CarryOverEnum carryOverEnum) throws RuntimeException{
            if(position>=counter.length)
                return;

            byte val = counter[position];
            val++;

            if (val>=10){
                counter[position]=0;
                if(position<counter.length)
                    add(position+1,CarryOverEnum.CarryOverValue);
                else
                    throw new RuntimeException("Please resize the AtomicCounter");
                //should this resize automatically, what strategy
            }else{
                counter[position]=val;
                return;
            }
        }
        public void print(){
            for(int i=counter.length-1;i>=0;i--){
                System.out.print(counter[i]);
            }
        }
    }

    public enum CarryOverEnum{
        CarryOverValue,
        NoCarryOverValue
    }
}

