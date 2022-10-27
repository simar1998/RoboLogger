import openpyscad as ops
from openpyscad import Sphere, Cube, Cylinder, Polyhedron


def print_hi(name):
    # Use a breakpoint in the code line below to debug your script.
    Sphere(r=10, _fn=100)
    Cube([10, 10, 10])
    Cylinder(h=10, r=10)
    p = Polyhedron(
        points=[
            [10, 10, 0], [10, -10, 0], [-10, -10, 0], [-10, 10, 0], [0, 0, 10]
        ],
        faces=[
            [0, 1, 4], [1, 2, 4], [2, 3, 4], [3, 0, 4], [1, 0, 3], [2, 1, 3]
        ]
    )


# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    print_hi('PyCharm')