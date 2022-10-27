from cadquery import cq


def print_hi(name):
    # Use a breakpoint in the code line below to debug your script.
    height = 60.0
    width = 80.0
    thickness = 10.0

    # make the base
    result = cq.Workplane("XY").box(height, width, thickness)

    # Render the solid
    show_object(result)


# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    print_hi('PyCharm')