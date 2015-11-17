Building jruby-complete:

1) Download jruby-complete-x.x.x.jar
2) java -jar jruby-complete-x.x.x.jar -S gem install -i ./compass compass
3) jar uf jruby-complete-x.x.x.jar -C compass .
4) java -jar jruby-complete-x.x.x.jar -S gem list