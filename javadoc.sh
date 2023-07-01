javadoc -d web -private src/*.java
git add web # necessário apenas na primeira vez
git commit -m "javadoc"
git subtree push --prefix web Grafos web
git reset --hard HEAD^
