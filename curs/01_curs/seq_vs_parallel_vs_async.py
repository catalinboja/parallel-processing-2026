import time
import asyncio
from concurrent.futures import ProcessPoolExecutor


ITERATIONS = 15_000_000
WAIT_SECONDS = 2


# ============================================================
# I/O-BOUND WORK
# ============================================================

def io_work(name):
    print(f"{name}: starting")
    time.sleep(WAIT_SECONDS)
    print(f"{name}: finished")


async def io_work_async(name):
    print(f"{name}: starting")
    await asyncio.sleep(WAIT_SECONDS)
    print(f"{name}: finished")


# ============================================================
# CPU-INTENSIVE WORK
# ============================================================

def cpu_work(name):
    print(f"{name}: starting")

    x = 0

    for i in range(ITERATIONS):
        x = (x + i) * 2
        x %= 1_000_003

    print(f"{name}: finished")

    return x

# ============================================================
# SEQUENTIAL
# ============================================================

def sequential():

    print("\n--- SEQUENTIAL ---")

    start = time.perf_counter()

    cpu_work("Task 1")
    cpu_work("Task 2")
    cpu_work("Task 3")

    print(f"Total duration: {time.perf_counter() - start:.2f} s")


# ============================================================
# SEQUENTIAL I/O
# ============================================================

def sequential_io():
    print("\n--- SEQUENTIAL I/O ---")

    start = time.perf_counter()

    io_work("Task 1")
    io_work("Task 2")
    io_work("Task 3")

    print(f"Total duration: {time.perf_counter() - start:.2f} s")


# ============================================================
# ASYNCHRONOUS I/O
# ============================================================

async def asynchronous():
    print("\n--- ASYNCHRONOUS I/O ---")

    start = time.perf_counter()

    await asyncio.gather(
        io_work_async("Task 1"),
        io_work_async("Task 2"),
        io_work_async("Task 3")
    )

    print(f"Total duration: {time.perf_counter() - start:.2f} s")


# ============================================================
# PARALLEL
# ============================================================

def parallel():

    print("\n--- PARALLEL CPU WORK ---")

    start = time.perf_counter()

    with ProcessPoolExecutor(max_workers=3) as executor:

        futures = [
            executor.submit(cpu_work, "Task 1"),
            executor.submit(cpu_work, "Task 2"),
            executor.submit(cpu_work, "Task 3")
        ]

        for f in futures:
            f.result()

    print(f"Total duration: {time.perf_counter() - start:.2f} s")


# ============================================================
# RUN
# ============================================================

if __name__ == "__main__":

    sequential_io()

    asyncio.run(asynchronous())

    sequential()
    parallel()