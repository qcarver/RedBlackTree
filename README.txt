Running main class App will instantiate a tree and do several tests on it.
Output will be as follows (where Integer.MIN is the key value of the
Sentinel node)

Printing tree after insertions...
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<tree>
    <root key="3" color="BLACK">
        <right key="7" color="RED">
            <right key="11" color="BLACK">
                <right key="13" color="RED">
                    <right key="14" color="BLACK">
                        <right key="15" color="RED">
                            <right key="-2147483648" color="BLACK"/>
                            <left key="-2147483648" color="BLACK"/>
                        </right>
                        <left key="-2147483648" color="BLACK"/>
                    </right>
                    <left key="12" color="BLACK">
                        <right key="-2147483648" color="BLACK"/>
                        <left key="-2147483648" color="BLACK"/>
                    </left>
                </right>
                <left key="9" color="RED">
                    <right key="10" color="BLACK">
                        <right key="-2147483648" color="BLACK"/>
                        <left key="-2147483648" color="BLACK"/>
                    </right>
                    <left key="8" color="BLACK">
                        <right key="-2147483648" color="BLACK"/>
                        <left key="-2147483648" color="BLACK"/>
                    </left>
                </left>
            </right>
            <left key="5" color="BLACK">
                <right key="6" color="BLACK">
                    <right key="-2147483648" color="BLACK"/>
                    <left key="-2147483648" color="BLACK"/>
                </right>
                <left key="4" color="BLACK">
                    <right key="-2147483648" color="BLACK"/>
                    <left key="-2147483648" color="BLACK"/>
                </left>
            </left>
        </right>
        <left key="1" color="BLACK">
            <right key="2" color="BLACK">
                <right key="-2147483648" color="BLACK"/>
                <left key="-2147483648" color="BLACK"/>
            </right>
            <left key="0" color="BLACK">
                <right key="-2147483648" color="BLACK"/>
                <left key="-2147483648" color="BLACK"/>
            </left>
        </left>
    </root>
</tree>
Printing tree after deletions...
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<tree>
    <root key="8" color="BLACK">
        <right key="12" color="BLACK">
            <right key="14" color="BLACK">
                <right key="-2147483648" color="BLACK"/>
                <left key="13" color="RED">
                    <right key="-2147483648" color="BLACK"/>
                    <left key="-2147483648" color="BLACK"/>
                </left>
            </right>
            <left key="10" color="BLACK">
                <right key="-2147483648" color="BLACK"/>
                <left key="-2147483648" color="BLACK"/>
            </left>
        </right>
        <left key="3" color="BLACK">
            <right key="4" color="BLACK">
                <right key="-2147483648" color="BLACK"/>
                <left key="-2147483648" color="BLACK"/>
            </right>
            <left key="0" color="BLACK">
                <right key="-2147483648" color="BLACK"/>
                <left key="-2147483648" color="BLACK"/>
            </left>
        </left>
    </root>
</tree>
After delete black height of tree is 3
